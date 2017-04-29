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
    private  String atributCash;
    @Column
    private  String fullCash;

    public String getAtributCash() {
        return atributCash;
    }

    public void setAtributCash(String atributCash) {
        this.atributCash = atributCash;
    }

    public String getFullCash() {
        return fullCash;
    }

    public void setFullCash(String fullCash) {
        this.fullCash = fullCash;
    }
}
