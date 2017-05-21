package papka.pahan.converterlub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import papka.pahan.converterlub.R;
import papka.pahan.converterlub.db.ModelDataBaseCash;

/**
 * Created by admin on 16.05.2017.
 */

public class ShareAdapter extends BaseAdapter {
    private Context context;
    private List<ModelDataBaseCash> modelDataBaseCashes;
    private LayoutInflater inflater;

    public ShareAdapter(Context context, List<ModelDataBaseCash> data) {
        this.context = context;
        modelDataBaseCashes = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return modelDataBaseCashes.size();
    }

    @Override
    public Object getItem(int i) {
        return modelDataBaseCashes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return modelDataBaseCashes.get(i).getIdd();
    }

    @Override
    public View getView(int i, View mView, ViewGroup viewGroup) {
        View view = mView;
        if (view == null) {
            view = inflater.inflate(R.layout.cash_share_item, viewGroup, false);
        }

        ModelDataBaseCash currencyOrg = modelDataBaseCashes.get(i);

        TextView tvId = (TextView) view.findViewById(R.id.tv_cash_name);
        TextView tvAskBid = (TextView) view.findViewById(R.id.tv_cash_value);

        tvId.setText(currencyOrg.getCashNameAttribute());
        String s = currencyOrg.getAsk().substring(0,6);
        String s1 = currencyOrg.getBid().substring(0,6);
        tvAskBid.setText(s + "/" + s1);
        return view;
    }
}
