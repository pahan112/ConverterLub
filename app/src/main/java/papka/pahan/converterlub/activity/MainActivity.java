package papka.pahan.converterlub.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.adapter.BankAdapter;
import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.interfase.OnClickImage;
import papka.pahan.converterlub.service.BankService;

public class MainActivity extends AppCompatActivity implements OnClickImage {
    @BindView(R.id.rv_bank_list)
    RecyclerView mRecyclerViewBank;
    @BindView(R.id.search_bank)
    SearchView mSearchViewBank;
    @BindView(R.id.sr_bank)
    SwipeRefreshLayout mBankSwipeRefreshLayout;
    @BindView(R.id.pb_bank)
    ProgressBar mBankProgressBar;

    private List<ModelDataBaseBank> mModelDataBaseBanks = new ArrayList<>();
    private List<ModelDataBaseBank> mModelDataBaseBanksSearch = new ArrayList<>();

    private BankAdapter mBankAdapter;

    private ResultReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBankProgressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, BankService.class);
        resultReceiveBank();
        intent.putExtra("receiver", mReceiver);
        startService(intent);
        swipeRefreshLayout();
        search();
    }

    public void resultReceiveBank() {
        mReceiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == 200) {
                    mBankProgressBar.setVisibility(View.INVISIBLE);
                    mModelDataBaseBanks.clear();
                    mModelDataBaseBanks = new Select().from(ModelDataBaseBank.class).queryList();
                    mRecyclerViewBank.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    mBankAdapter = new BankAdapter(mModelDataBaseBanks, MainActivity.this);
                    mRecyclerViewBank.setAdapter(mBankAdapter);
                    mBankAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    private void swipeRefreshLayout(){
        mBankSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resultReceiveBank();
                mBankSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClickPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onClicksLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void onClickSetting(ModelDataBaseBank modelDataBaseBank) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.BANK_DETAILS, modelDataBaseBank);
        startActivity(intent);
    }

    @Override
    public void onClickMap(ModelDataBaseBank modelDataBaseBank) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(MapActivity.BANK_MAP, modelDataBaseBank);
        startActivity(intent);
    }

    private void search() {
        ((EditText) mSearchViewBank.findViewById(R.id.search_src_text)).setTextColor(Color.WHITE);
        mSearchViewBank.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mBankAdapter.setBankList(mModelDataBaseBanks);
                } else {
                    mModelDataBaseBanksSearch.clear();
                    for (ModelDataBaseBank bank : mModelDataBaseBanks) {
                        if (bank.getTitleDb().toLowerCase().contains(newText.toLowerCase()) || bank.getCityIdDb().toLowerCase().contains(newText.toLowerCase())) {
                            mModelDataBaseBanksSearch.add(bank);
                        }
                        mBankAdapter.setBankList(mModelDataBaseBanksSearch);
                    }

                }
                return false;
            }
        });
    }
}
