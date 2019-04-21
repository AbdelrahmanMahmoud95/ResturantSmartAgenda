package com.agenda.smart.restaurant.model;

/**
 * Created by User on 22/10/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsTaxes {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("messages")
    @Expose
    private List<String> messages = null;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @SerializedName("tax")
    @Expose
    private double tax;

    @SerializedName("total_cost")
    @Expose
    private double totalCost;

    @SerializedName("total_cost_after_tax")
    @Expose
    private double totalCostAfterTax;
    @SerializedName("total_tax")
    @Expose
    private double totalTax;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("orders")
    @Expose
    private List<OrderTax> orders = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCostAfterTax() {
        return totalCostAfterTax;
    }

    public void setTotalCostAfterTax(double totalCostAfterTax) {
        this.totalCostAfterTax = totalCostAfterTax;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<OrderTax> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderTax> orders) {
        this.orders = orders;
    }


}