package com.agenda.smart.restaurant.model;

/**
 * Created by User on 24/10/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsEarning {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("earning")
    @Expose
    private double earning;
    @SerializedName("purchases_cost")
    @Expose
    private double purchasesCost;
    @SerializedName("earnings_cost")
    @Expose
    private double earningsCost;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("purchases")
    @Expose
    private List<Purchase> purchases = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public double getPurchasesCost() {
        return purchasesCost;
    }

    public void setPurchasesCost(double purchasesCost) {
        this.purchasesCost = purchasesCost;
    }

    public double getEarningsCost() {
        return earningsCost;
    }

    public void setEarningsCost(double earningsCost) {
        this.earningsCost = earningsCost;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

}