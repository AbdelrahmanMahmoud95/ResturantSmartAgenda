package com.agenda.smart.restaurant.model;

/**
 * Created by User on 23/10/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsOrderDaily {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("orders_count")
    @Expose
    private int ordersCount;
    @SerializedName("earning")
    @Expose
    private double earning;
    @SerializedName("workers_count")
    @Expose
    private int workersCount;
    @SerializedName("orders")
    @Expose
    private List<OrderDaily> orders = null;
    @SerializedName("heading")
    @Expose
    private String heading;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public int getWorkersCount() {
        return workersCount;
    }

    public void setWorkersCount(int workersCount) {
        this.workersCount = workersCount;
    }

    public List<OrderDaily> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDaily> orders) {
        this.orders = orders;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

}