package papka.pahan.converterlub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.db.ModelDataBaseBank;

/**
 * Created by admin on 01.05.2017.
 */

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    private List<ModelDataBaseBank> modelDataBaseBanks;
    private OnClickBankItemListener mOnClickBankItemListener;

    public BankAdapter(List<ModelDataBaseBank> modelDataBaseBanks , OnClickBankItemListener mOnClickBankItemListener) {
        this.mOnClickBankItemListener = mOnClickBankItemListener;
        this.modelDataBaseBanks = modelDataBaseBanks;
    }

    @Override
    public BankAdapter.BankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false));
    }

    @Override
    public void onBindViewHolder(BankAdapter.BankViewHolder holder, int position) {
        holder.bind(modelDataBaseBanks.get(position));
    }

    @Override
    public int getItemCount() {
        return modelDataBaseBanks.size();
    }

    public void setBankList(List<ModelDataBaseBank> modelBankList) {
        this.modelDataBaseBanks = modelBankList;
        notifyDataSetChanged();
    }

    public class BankViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_bank)
        TextView titleBankTextView;
        @BindView(R.id.tv_address_bank)
        TextView addressBankTextView;
        @BindView(R.id.tv_city_bank)
        TextView cityBankTextView;
        @BindView(R.id.tv_phone_bank)
        TextView phoneBankTextView;
        @BindView(R.id.tv_region_bank)
        TextView regionBankTextView;

        @BindView(R.id.iv_browser)
        ImageView imageViewBrowser;
        @BindView(R.id.iv_call)
        ImageView imageViewCall;
        @BindView(R.id.iv_map)
        ImageView imageViewMap;
        @BindView(R.id.iv_setting)
        ImageView imageViewSetting;

        public BankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ModelDataBaseBank modelDataBaseBanks) {
            titleBankTextView.setText(modelDataBaseBanks.getTitleDb());
            addressBankTextView.setText("Адрес: " + modelDataBaseBanks.getAddressDb());
            cityBankTextView.setText(modelDataBaseBanks.getCityIdDb());
            phoneBankTextView.setText("Тел.: " + modelDataBaseBanks.getPhoneDb());
            regionBankTextView.setText(modelDataBaseBanks.getRegionIDb());
            imageViewBrowser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickBankItemListener.onClicksLink(modelDataBaseBanks.getLinkDb());
                }
            });
            imageViewCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickBankItemListener.onClickPhone(modelDataBaseBanks.getPhoneDb());
                }
            });
            imageViewMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickBankItemListener.onClickMap(modelDataBaseBanks);
                }
            });
            imageViewSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickBankItemListener.onClickSetting(modelDataBaseBanks);
                }
            });
        }
    }

    public interface OnClickBankItemListener {
        void onClickPhone(String phone);
        void onClicksLink (String link);
        void onClickSetting(ModelDataBaseBank modelDataBaseBank);
        void onClickMap(ModelDataBaseBank modelDataBaseBank);
    }
}
