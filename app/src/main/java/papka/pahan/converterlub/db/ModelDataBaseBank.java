package papka.pahan.converterlub.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by admin on 29.04.2017.
 */
@Table(database = BankDataBase.class)
public class ModelDataBaseBank extends BaseModel {
    @PrimaryKey
    @Column
    private String idDb;
    @Column
    private String titleDb;
    @Column
    private String regionIDb;
    @Column
    private  String cityIdDb;
    @Column
    private  String phoneDb;
    @Column
    private  String addressDb;

    public String getIdDb() {
        return idDb;
    }

    public String getTitleDb() {
        return titleDb;
    }

    public void setTitleDb(String titleDb) {
        this.titleDb = titleDb;
    }

    public String getRegionIDb() {
        return regionIDb;
    }

    public void setRegionIDb(String regionIDb) {
        this.regionIDb = regionIDb;
    }

    public String getCityIdDb() {
        return cityIdDb;
    }

    public void setCityIdDb(String cityIdDb) {
        this.cityIdDb = cityIdDb;
    }

    public String getPhoneDb() {
        return phoneDb;
    }

    public void setPhoneDb(String phoneDb) {
        this.phoneDb = phoneDb;
    }

    public String getAddressDb() {
        return addressDb;
    }

    public void setAddressDb(String addressDb) {
        this.addressDb = addressDb;
    }

    public String getLinkDb() {
        return linkDb;
    }

    public void setLinkDb(String linkDb) {
        this.linkDb = linkDb;
    }

    @Column

    private String linkDb;

    public void setIdDb(String idDb) {
        this.idDb = idDb;
    }
}
