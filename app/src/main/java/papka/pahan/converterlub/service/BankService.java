package papka.pahan.converterlub.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.model.ModelBank;

/**
 * Created by admin on 29.04.2017.
 */

public class BankService extends IntentService {

    final String LOG_TAG = "myLog";
    private ModelBank modelBank;
    public BankService() {
        super("service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void connect(String http) throws IOException, JSONException {
        URL url = new URL(http);

        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.setRequestProperty("Content-length", "0");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        Gson gson = new Gson();
        modelBank = gson.fromJson(sb.toString(), ModelBank.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            connect("http://resources.finance.ua/ru/public/currency-cash.json");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        ModelDataBaseBank modelDataBaseBank = new ModelDataBaseBank();
        for (int i = 0; i < modelBank.getOrganizations().size(); i++) {
            modelDataBaseBank.setIdDb(modelBank.getOrganizations().get(i).getId());
            modelDataBaseBank.setTitleDb(modelBank.getOrganizations().get(i).getTitle());
            modelDataBaseBank.setRegionIDb(modelBank.getRegions().get(modelBank.getOrganizations().get(i).getRegionId()));
            modelDataBaseBank.setCityIdDb(modelBank.getCities().get(modelBank.getOrganizations().get(i).getCityId()));
            modelDataBaseBank.setPhoneDb(modelBank.getOrganizations().get(i).getPhone());
            modelDataBaseBank.setAddressDb(modelBank.getOrganizations().get(i).getAddress());
            modelDataBaseBank.setLinkDb(modelBank.getOrganizations().get(i).getLink());
            modelDataBaseBank.save();
        }
    }
}
