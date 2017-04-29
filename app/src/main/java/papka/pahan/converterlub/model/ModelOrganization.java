package papka.pahan.converterlub.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by admin on 29.04.2017.
 */

public class ModelOrganization {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("regionId")
    private String regionId;
    @SerializedName("cityId")
    private String cityId;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public HashMap<String, ModelCurrencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, ModelCurrencies> currencies) {
        this.currencies = currencies;
    }

    @SerializedName("link")
    private String link;
    @SerializedName("currencies")
    private HashMap<String,ModelCurrencies> currencies;

}
