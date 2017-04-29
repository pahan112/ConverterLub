package papka.pahan.converterlub.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 29.04.2017.
 */

public class ModelBank {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ModelOrganization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<ModelOrganization> organizations) {
        this.organizations = organizations;
    }

    public HashMap<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, String> currencies) {
        this.currencies = currencies;
    }

    public HashMap<String, String> getRegions() {
        return regions;
    }

    public void setRegions(HashMap<String, String> regions) {
        this.regions = regions;
    }

    public HashMap<String, String> getCities() {
        return cities;
    }

    public void setCities(HashMap<String, String> cities) {
        this.cities = cities;
    }

    @SerializedName("date")
    private String date;
    @SerializedName("organizations")
    private List<ModelOrganization> organizations;
    @SerializedName("currencies")
    private HashMap<String,String> currencies;
    @SerializedName("regions")
    private HashMap<String,String> regions;
    @SerializedName("cities")
    private HashMap<String,String> cities;
}
