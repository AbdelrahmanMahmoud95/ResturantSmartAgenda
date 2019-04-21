package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 8/7/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("order")
    @Expose
    private Order order;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}