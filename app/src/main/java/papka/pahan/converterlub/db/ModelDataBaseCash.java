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
    long _id;

    @Column
    private String bankId;
    @Column
    private String cashNameAtribute;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCashNameAtribute() {
        return cashNameAtribute;
    }

    public void setCashNameAtribute(String cashNameAtribute) {
        this.cashNameAtribute = cashNameAtribute;
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

    @Column

    private String ask;
    @Column
    private String bid;
}
