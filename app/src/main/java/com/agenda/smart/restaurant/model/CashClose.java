package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 11/12/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashClose {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("cashiers")
    @Expose
    private List<Cashier> cashiers = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CashClose withStatus(int status) {
        this.status = status;
        return this;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public CashClose withCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
        return this;
    }

}