package papka.pahan.converterlub.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 29.04.2017.
 */

public class ModelCurrencies {

    @SerializedName("ask")
    private String ask;
    @SerializedName("bid")
    private String bid;

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
