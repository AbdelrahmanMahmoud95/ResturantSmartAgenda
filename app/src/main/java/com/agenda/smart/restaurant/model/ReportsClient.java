package com.agenda.smart.restaurant.model;

/**
 * Created by User on 29/10/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsClient {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
