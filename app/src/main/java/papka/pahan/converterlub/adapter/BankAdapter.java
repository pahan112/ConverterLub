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
import papka.pahan.converterlub.interfase.OnClickImage;

/**
 * Created by admin on 01.05.2017.
 */

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    private List<ModelDataBaseBank> modelDataBaseBanks;
    private OnClickImage mOnClickImage;
    public BankAdapter(List<ModelDataBaseBank> modelDataBaseBanks , OnClickImage mOnClickImage) {
        this.mOnClickImage = mOnClickImage;
        this.modelDataBaseBanks = modelDataBaseBanks;
    }

    public void setBankList(List<ModelDataBaseBank> modelBankList) {
        this.modelDataBaseBanks = modelBankList;
        notifyDataSetChanged();
    }
    @Override
    public BankAdapter.BankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_list, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BankAdapter.BankViewHolder holder, int position) {
        holder.bind(modelDataBaseBanks.get(position));
    }

    @Override
    public int getItemCount() {
        return modelDataBaseBanks.size();
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

        @BindView(R.id.iv_brouser)
        ImageView imageViewBrouser;
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
            imageViewBrouser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickImage.onClicksLink(modelDataBaseBanks.getLinkDb());
                }
            });
            imageViewCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickImage.onClickPhone(modelDataBaseBanks.getPhoneDb());
                }
            });
            imageViewMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickImage.onClickMap(modelDataBaseBanks);
                }
            });
            imageViewSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickImage.onClickSetting(modelDataBaseBanks);
                }
            });
        }
    }
}
