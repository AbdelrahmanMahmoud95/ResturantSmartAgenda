package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 11/12/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashCloseDetails {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("cashier")
    @Expose
    private Cashier cashier;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CashCloseDetails withStatus(int status) {
        this.status = status;
        return this;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public CashCloseDetails withCashier(Cashier cashier) {
        this.cashier = cashier;
        return this;
    }

}