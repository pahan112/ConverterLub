package papka.pahan.converterlub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.db.ModelDataBaseCash;
import papka.pahan.converterlub.db.ModelDataBaseCurrencies;

/**
 * Created by admin on 08.05.2017.
 */

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.CashViewHolder> {

    private List<ModelDataBaseCash> mModelDataBaseCashes;
    private List<ModelDataBaseCurrencies> mModelDataBaseCurrencies;

    public CashAdapter(List<ModelDataBaseCash> mModelDataBaseCashes , List<ModelDataBaseCurrencies> mModelDataBaseCurrencies) {
        this.mModelDataBaseCashes = mModelDataBaseCashes;
        this.mModelDataBaseCurrencies = mModelDataBaseCurrencies;
    }

    @Override
    public CashAdapter.CashViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CashViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cash, parent, false));
    }

    @Override
    public void onBindViewHolder(CashAdapter.CashViewHolder holder, int position) {
        holder.bind(mModelDataBaseCashes.get(position), mModelDataBaseCurrencies.get(position));
    }

    @Override
    public int getItemCount() {
        return mModelDataBaseCashes.size();
    }

    public class CashViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_currency)
        TextView currentTextView;
        @BindView(R.id.tv_ask)
        TextView askTextView;
        @BindView(R.id.tv_bid)
        TextView bidTextView;

        public CashViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
        public void bind(ModelDataBaseCash modelDataBaseCash ,ModelDataBaseCurrencies modelDataBaseCurrencies){
            currentTextView.setText(modelDataBaseCurrencies.getFullCash());
            askTextView.setText(modelDataBaseCash.getAsk());
            bidTextView.setText(modelDataBaseCash.getBid());
        }
    }
}
