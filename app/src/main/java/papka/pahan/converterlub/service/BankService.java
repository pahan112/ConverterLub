package papka.pahan.converterlub.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.db.ModelDataBaseCash;
import papka.pahan.converterlub.db.ModelDataBaseCurrencies;
import papka.pahan.converterlub.model.ModelBank;
import papka.pahan.converterlub.tools.PreferenceManager;

/**
 * Created by admin on 29.04.2017.
 */

public class BankService extends IntentService {

    private ModelBank mModelBank;
    private ResultReceiver mRec;

    public BankService() {
        super("service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public boolean isInternetWorking() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
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
            sb.append(line).append("\n");
        }
        br.close();
        Gson gson = new Gson();
        mModelBank = gson.fromJson(sb.toString(), ModelBank.class);
    }

    private void createModelDataBaseBank() {
        Delete.tables(ModelDataBaseBank.class);
        ModelDataBaseBank modelDataBaseBank = new ModelDataBaseBank();
        for (int i = 0; i < mModelBank.getOrganizations().size(); i++) {
            modelDataBaseBank.setIdDb(mModelBank.getOrganizations().get(i).getId());
            modelDataBaseBank.setTitleDb(mModelBank.getOrganizations().get(i).getTitle());
            modelDataBaseBank.setRegionIDb(mModelBank.getRegions().get(mModelBank.getOrganizations().get(i).getRegionId()));
            modelDataBaseBank.setCityIdDb(mModelBank.getCities().get(mModelBank.getOrganizations().get(i).getCityId()));
            modelDataBaseBank.setPhoneDb(mModelBank.getOrganizations().get(i).getPhone());
            modelDataBaseBank.setAddressDb(mModelBank.getOrganizations().get(i).getAddress());
            modelDataBaseBank.setLinkDb(mModelBank.getOrganizations().get(i).getLink());
            modelDataBaseBank.save();
        }
    }

    private void createModelDataBaseCash() {
        Delete.tables(ModelDataBaseCash.class);
        for (int i = 0; i < mModelBank.getOrganizations().size(); i++) {
            for (String s : mModelBank.getOrganizations().get(i).getCurrencies().keySet()) {
                ModelDataBaseCash modelDataBaseCash = new ModelDataBaseCash();
                modelDataBaseCash.setCashNameAttribute(s);
                modelDataBaseCash.setAsk(mModelBank.getOrganizations().get(i).getCurrencies().get(s).getAsk());
                modelDataBaseCash.setBid(mModelBank.getOrganizations().get(i).getCurrencies().get(s).getBid());
                modelDataBaseCash.setBankId(mModelBank.getOrganizations().get(i).getId());
                modelDataBaseCash.save();
            }
        }
    }

    private void createModelDataBaseCurrencies() {
        Delete.tables(ModelDataBaseCurrencies.class);
        ModelDataBaseCurrencies modelDataBaseCurrencies = new ModelDataBaseCurrencies();
        for (String s1 : mModelBank.getCurrencies().keySet()) {
            modelDataBaseCurrencies.setAttributeCash(s1);
            modelDataBaseCurrencies.setFullCash(mModelBank.getCurrencies().get(s1));
            modelDataBaseCurrencies.save();
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mRec = intent.getParcelableExtra("receiver");
        if (isInternetWorking()) {

            try {
                connect("http://resources.finance.ua/ru/public/currency-cash.json");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            getPref();
            if (mRec != null) {
                mRec.send(200, null);
            }
        }
        else {
            if (mRec != null) {
                mRec.send(200, null);
            }
        }
    }
    private void getPref() {
        if (PreferenceManager.loadStringParam(this, PreferenceManager.PARAM_LAST_UPDATE).equals(mModelBank.getDate())) {
            if (mRec != null) {
                mRec.send(200, null);
            }
        } else {
            PreferenceManager.storeStringParam(this, PreferenceManager.PARAM_LAST_UPDATE, mModelBank.getDate());
            createModelDataBaseBank();
            createModelDataBaseCash();
            createModelDataBaseCurrencies();
        }
    }
}
