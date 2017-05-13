package papka.pahan.converterlub.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by admin on 29.04.2017.
 */
@Table(database = BankDataBase.class)
public class ModelDataBaseCash extends BaseModel{

    @Column
    @PrimaryKey(autoincrement = true)
    public long idd;
    @Column
    private String bankId;
    @Column
    private String cashNameAttribute;
    @Column
    private String ask;
    @Column
    private String bid;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCashNameAttribute() {
        return cashNameAttribute;
    }

    public void setCashNameAttribute(String cashNameAttribute) {
        this.cashNameAttribute = cashNameAttribute;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


}
