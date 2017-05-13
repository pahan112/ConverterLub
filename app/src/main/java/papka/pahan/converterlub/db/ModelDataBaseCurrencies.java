package papka.pahan.converterlub.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by admin on 29.04.2017.
 */
@Table(database = BankDataBase.class)
public class ModelDataBaseCurrencies extends BaseModel {

    @PrimaryKey
    @Column
    private  String attributeCash;
    @Column
    private  String fullCash;

    public String getAttributeCash() {
        return attributeCash;
    }

    public void setAttributeCash(String attributeCash) {
        this.attributeCash = attributeCash;
    }

    public String getFullCash() {
        return fullCash;
    }

    public void setFullCash(String fullCash) {
        this.fullCash = fullCash;
    }
}
