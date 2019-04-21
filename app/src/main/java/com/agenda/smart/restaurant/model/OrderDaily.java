package com.agenda.smart.restaurant.model;

/**
 * Created by User on 23/10/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDaily {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("serial")
    @Expose
    private int serial;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("tax")
    @Expose
    private int tax;
    @SerializedName("table_id")
    @Expose
    private int tableId;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("delivery_cost")
    @Expose
    private double deliveryCost;
    @SerializedName("discount")
    @Expose
    private double discount;
    @SerializedName("customer_id")
    @Expose
    private int customerId;
    @SerializedName("waiter_id")
    @Expose
    private int waiterId;
    @SerializedName("cashier_id")
    @Expose
    private int cashierId;
    @SerializedName("driver_id")
    @Expose
    private int driverId;
    @SerializedName("visa_cost")
    @Expose
    private double visaCost;
    @SerializedName("later")
    @Expose
    private double later;
    @SerializedName("paid_later")
    @Expose
    private double paidLater;
    @SerializedName("trader_id")
    @Expose
    private int traderId;
    @SerializedName("shift_closed")
    @Expose
    private int shiftClosed;
    @SerializedName("delivered_at")
    @Expose
    private String deliveredAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("returns_cost")
    @Expose
    private double returnsCost;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("status_name")
    @Expose
    private String statusName;
    @SerializedName("cost_with_tax")
    @Expose
    private double costWithTax;
    @SerializedName("cost_with_delivery")
    @Expose
    private double costWithDelivery;
    @SerializedName("cost_after_discount")
    @Expose
    private double costAfterDiscount;
    @SerializedName("net_cost")
    @Expose
    private double netCost;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("cashier")
    @Expose
    private Cashier cashier;
    @SerializedName("waiter")
    @Expose
    private Waiter waiter;

    @SerializedName("driver")
    @Expose
    private Driver driver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public double getVisaCost() {
        return visaCost;
    }

    public void setVisaCost(double visaCost) {
        this.visaCost = visaCost;
    }

    public double getLater() {
        return later;
    }

    public void setLater(double later) {
        this.later = later;
    }

    public double getPaidLater() {
        return paidLater;
    }

    public void setPaidLater(double paidLater) {
        this.paidLater = paidLater;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public int getShiftClosed() {
        return shiftClosed;
    }

    public void setShiftClosed(int shiftClosed) {
        this.shiftClosed = shiftClosed;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getReturnsCost() {
        return returnsCost;
    }

    public void setReturnsCost(double returnsCost) {
        this.returnsCost = returnsCost;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public double getCostWithTax() {
        return costWithTax;
    }

    public void setCostWithTax(double costWithTax) {
        this.costWithTax = costWithTax;
    }

    public double getCostWithDelivery() {
        return costWithDelivery;
    }

    public void setCostWithDelivery(double costWithDelivery) {
        this.costWithDelivery = costWithDelivery;
    }

    public double getCostAfterDiscount() {
        return costAfterDiscount;
    }

    public void setCostAfterDiscount(double costAfterDiscount) {
        this.costAfterDiscount = costAfterDiscount;
    }

    public double getNetCost() {
        return netCost;
    }

    public void setNetCost(double netCost) {
        this.netCost = netCost;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}