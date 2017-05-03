package papka.pahan.converterlub.interfase;

import papka.pahan.converterlub.db.ModelDataBaseBank;

/**
 * Created by admin on 03.05.2017.
 */

public interface OnClickImage {
    void onClickPhone(String phone);
    void onClicksLink (String link);
    void onClickSetting(ModelDataBaseBank modelDataBaseBank);
    void onClickMap(String address);
}
