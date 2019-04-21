package com.agenda.smart.restaurant.model;

/**
 * Created by User on 29/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Return {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("order_detail_id")
    @Expose
    private int orderDetailId;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("cashier_id")
    @Expose
    private int cashierId;
    @SerializedName("shift_closed")
    @Expose
    private int shiftClosed;
    @SerializedName("paid")
    @Expose
    private double paid;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("total")
    @Expose
    private double total;
    @SerializedName("detail")
    @Expose
    private ReturnDetail detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getShiftClosed() {
        return shiftClosed;
    }

    public void setShiftClosed(int shiftClosed) {
        this.shiftClosed = shiftClosed;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ReturnDetail getDetail() {
        return detail;
    }

    public void setDetail(ReturnDetail detail) {
        this.detail = detail;
    }


}
