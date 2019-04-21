package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 11/12/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShiftCost {

    @SerializedName("net_cost")
    @Expose
    private double netCost;
    @SerializedName("visa_cost")
    @Expose
    private double visaCost;
    @SerializedName("cash_cost")
    @Expose
    private double cashCost;
    @SerializedName("returns_cost")
    @Expose
    private double returnsCost;

    public double getNetCost() {
        return netCost;
    }

    public void setNetCost(double netCost) {
        this.netCost = netCost;
    }

    public double getVisaCost() {
        return visaCost;
    }

    public void setVisaCost(double visaCost) {
        this.visaCost = visaCost;
    }

    public double getCashCost() {
        return cashCost;
    }

    public void setCashCost(double cashCost) {
        this.cashCost = cashCost;
    }

    public double getReturnsCost() {
        return returnsCost;
    }

    public void setReturnsCost(double returnsCost) {
        this.returnsCost = returnsCost;
    }

    public String getCashCloseLink() {
        return cashCloseLink;
    }

    public void setCashCloseLink(String cashCloseLink) {
        this.cashCloseLink = cashCloseLink;
    }

    @SerializedName("cash_close_link")
    @Expose

    private String cashCloseLink;

}