package papka.pahan.converterlub.dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.adapter.ShareAdapter;
import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.db.ModelDataBaseCash;
import papka.pahan.converterlub.db.ModelDataBaseCash_Table;

/**
 * Created by admin on 08.05.2017.
 */

public class ShareDialog extends DialogFragment {

    private ModelDataBaseBank mModelDataBaseBank;
    private List<ModelDataBaseCash> mModelDataBaseCash = new ArrayList<>();

    public static final String SHARE_DIALOG = "share_dialog";

    @BindView(R.id.iv_share_dialog)
    ImageView mShareImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        mModelDataBaseBank = (ModelDataBaseBank) getArguments().getSerializable(SHARE_DIALOG);
        mModelDataBaseCash.clear();
        mModelDataBaseCash.addAll(SQLite.select().from(ModelDataBaseCash.class).where(ModelDataBaseCash_Table.bankId.is(mModelDataBaseBank.getIdDb())).queryList());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_share, null);
        ButterKnife.bind(this, view);
        mShareImageView.setImageBitmap(setShareImage());
        return  view;

    }

    public Bitmap setShareImage() {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bitmap_layout, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(mModelDataBaseBank.getTitleDb());

        TextView tvCity = (TextView) view.findViewById(R.id.tv_subtitle);
        tvCity.setText(mModelDataBaseBank.getRegionIDb() + '\n' + mModelDataBaseBank.getCityIdDb());

        ListView mListView = (ListView) view.findViewById(R.id.lv_BL);
        ShareAdapter mAdapter = new ShareAdapter(getActivity(), mModelDataBaseCash);
        mListView.setAdapter(mAdapter);

        mListView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mListView.layout(0, 0, mListView.getMeasuredWidth() * 2, mListView.getMeasuredHeight() * (mModelDataBaseCash.size()));

        mListView.setLayoutParams(new LinearLayout.LayoutParams(mListView.getMeasuredWidth() * 2, mListView.getMeasuredHeight() * (mModelDataBaseCash.size())));

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        final Bitmap viewBitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(viewBitmap);
        view.draw(canvas);
        return viewBitmap;
    }
    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    @OnClick(R.id.btn_share)
    void clickShareButton() {
        String pathToBmp = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), setShareImage(),"title", null);
        Uri bmpUri = Uri.parse(pathToBmp);
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        intent.setType("image/png");
        startActivity(intent);
    }
}
