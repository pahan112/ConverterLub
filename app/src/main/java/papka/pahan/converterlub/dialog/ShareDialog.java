package papka.pahan.converterlub.dialog;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.db.ModelDataBaseBank;
import papka.pahan.converterlub.db.ModelDataBaseCash;
import papka.pahan.converterlub.db.ModelDataBaseCash_Table;

/**
 * Created by admin on 08.05.2017.
 */

public class ShareDialog extends DialogFragment {

    public static final String SHARE_DIALOG= "share_dialog";

    private ModelDataBaseBank mModelDataBaseBank;
    private List<ModelDataBaseCash> mModelDataBaseCash = new ArrayList<>();

    private int mHeightImage = 200;
    private int mPaddingExchange = 130;
    private Bitmap b;

    @BindView(R.id.iv_share_dialog)
    ImageView mShareImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModelDataBaseBank = (ModelDataBaseBank) getArguments().getSerializable(SHARE_DIALOG);
        mModelDataBaseCash.clear();
        mModelDataBaseCash.addAll(SQLite.select().from(ModelDataBaseCash.class).where(ModelDataBaseCash_Table.bankId.is(mModelDataBaseBank.getIdDb())).queryList());
        for (int i = 0; i < mModelDataBaseCash.size(); i++) {
            mHeightImage += 35;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_share, container);
        ButterKnife.bind(this, view);
        setImageView();
        return view;
    }

    private  void setImageView(){
        b = Bitmap.createBitmap(305, mHeightImage, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(-1);
        c.drawRect(0, 0, 305, (float) mHeightImage, paint);
        paint.setTextSize(18);
        paint.setColor(Color.BLACK);
        c.drawText(mModelDataBaseBank.getTitleDb(), 16, 40, paint);
        paint.setTextSize(14);
        c.drawText(mModelDataBaseBank.getRegionIDb(), 16, 60, paint);
        c.drawText(mModelDataBaseBank.getCityIdDb(), 16, 80, paint);
        paint.setTextSize(18.0f);
        for (int i = 0; i < mModelDataBaseCash.size(); i++) {
            paint.setColor(Color.RED);
            c.drawText(( mModelDataBaseCash.get(i)).getCashNameAtribute(), 35, (float) mPaddingExchange, paint);
            paint.setColor(Color.BLACK);
            c.drawText((mModelDataBaseCash.get(i)).getAsk().substring(0, 5) + Operator.Operation.DIVISION + (mModelDataBaseCash.get(i)).getBid().substring(0, 5), 150, (float) this.mPaddingExchange, paint);
            mPaddingExchange += 45;
        }
        mShareImageView.setImageBitmap(b);
        int maxX = (int)((305 / 2) - (304 / 2));
        int maxY = (int)((mHeightImage/ 2) - (500 / 2));

        final int maxLeft = (maxX * -1);
        final int maxRight = maxX;
        final int maxTop = (maxY * -1);
        final int maxBottom = maxY;

        mShareImageView.setOnTouchListener(new View.OnTouchListener()
        {
            float downX, downY;
            int totalX, totalY;
            int scrollByX, scrollByY;
            public boolean onTouch(View view, MotionEvent event)
            {
                float currentX, currentY;
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        currentX = event.getX();
                        currentY = event.getY();
                        scrollByX = (int)(downX - currentX);
                        scrollByY = (int)(downY - currentY);
                        if (currentX > downX)
                        {
                            if (totalX == maxLeft)
                            {
                                scrollByX = 0;
                            }
                            if (totalX > maxLeft)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX < maxLeft)
                            {
                                scrollByX = maxLeft - (totalX - scrollByX);
                                totalX = maxLeft;
                            }
                        }
                        if (currentX < downX)
                        {
                            if (totalX == maxRight)
                            {
                                scrollByX = 0;
                            }
                            if (totalX < maxRight)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX > maxRight)
                            {
                                scrollByX = maxRight - (totalX - scrollByX);
                                totalX = maxRight;
                            }
                        }
                        if (currentY > downY)
                        {
                            if (totalY == maxTop)
                            {
                                scrollByY = 0;
                            }
                            if (totalY > maxTop)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY < maxTop)
                            {
                                scrollByY = maxTop - (totalY - scrollByY);
                                totalY = maxTop;
                            }
                        }
                        if (currentY < downY)
                        {
                            if (totalY == maxBottom)
                            {
                                scrollByY = 0;
                            }
                            if (totalY < maxBottom)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY > maxBottom)
                            {
                                scrollByY = maxBottom - (totalY - scrollByY);
                                totalY = maxBottom;
                            }
                        }

                        mShareImageView.scrollBy(scrollByX, scrollByY);
                        downX = currentX;
                        downY = currentY;
                        break;

                }
                return true;
            }
        });
    }

    @OnClick(R.id.bt_share_dialog)
    void clickShareButton(){

    }
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(1);
        return dialog;
    }
}
