package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 8/7/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOut {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}