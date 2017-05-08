package papka.pahan.converterlub.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.adapter.CashAdapter;
import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.db.ModelDataBaseCash;
import papka.pahan.converterlub.db.ModelDataBaseCash_Table;
import papka.pahan.converterlub.db.ModelDataBaseCurrencies;
import papka.pahan.converterlub.db.ModelDataBaseCurrencies_Table;

/**
 * Created by admin on 08.05.2017.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String BANK_DETAILS= "bank_details";
    private ModelDataBaseBank mModelDataBaseBank;
    @BindView(R.id.tv_title_setting)
    TextView mTitleSetingTextView;
    @BindView(R.id.tv_city_setting)
    TextView mCitySetingTextView;
    @BindView(R.id.tv_title_setting2)
    TextView mTitleSetingTextView2;
    @BindView(R.id.tv_link_setting)
    TextView mLinkSetingTextView;
    @BindView(R.id.tv_address_setting)
    TextView mAddressSetingTextView;
    @BindView(R.id.tv_phone_setting)
    TextView mPhoneSetingTextView;
    @BindView(R.id.rv_bank_setting)
    RecyclerView mCashRecyclerView;

    private List<ModelDataBaseCash> mModelDataBaseCashes = new ArrayList<>();
    private List<ModelDataBaseCurrencies> mModelDataBaseCurrencies = new ArrayList<>();
    private CashAdapter mCashAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_setting);
        ButterKnife.bind(this);

        mModelDataBaseBank = (ModelDataBaseBank) getIntent().getSerializableExtra(BANK_DETAILS);

        saveText();

        mModelDataBaseCashes.clear();
        mModelDataBaseCashes.addAll(SQLite.select().from(ModelDataBaseCash.class).where(ModelDataBaseCash_Table.bankId.is(mModelDataBaseBank.getIdDb())).queryList());
        mModelDataBaseCurrencies.clear();
        for (int i = 0; i < mModelDataBaseCashes.size() ; i++) {
            mModelDataBaseCurrencies.addAll(SQLite.select().from(ModelDataBaseCurrencies.class).where(ModelDataBaseCurrencies_Table.atributCash.is(( mModelDataBaseCashes.get(i)).getCashNameAtribute())).queryList());
        }
        mCashAdapter = new CashAdapter(mModelDataBaseCashes,mModelDataBaseCurrencies);
        mCashRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCashRecyclerView.setAdapter(mCashAdapter);
    }

    private void saveText(){
        mTitleSetingTextView.setText(mModelDataBaseBank.getTitleDb());
        mTitleSetingTextView2.setText(mModelDataBaseBank.getTitleDb());
        mCitySetingTextView.setText(mModelDataBaseBank.getCityIdDb());
        mLinkSetingTextView.setText(mModelDataBaseBank.getLinkDb());
        mAddressSetingTextView.setText("Адрес: " + mModelDataBaseBank.getAddressDb());
        mPhoneSetingTextView.setText("Тел.: " + mModelDataBaseBank.getPhoneDb());
    }

    @OnClick(R.id.iv_back_setting)
    void onClick(){
        onBackPressed();
    }
}
