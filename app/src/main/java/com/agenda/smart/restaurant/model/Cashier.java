package com.agenda.smart.restaurant.model;

/**
 * Created by User on 23/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cashier {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("first_order")
    @Expose
    private FirstOrder firstOrder;
    @SerializedName("last_order")
    @Expose
    private LastOrder lastOrder;

    public ShiftCost getShiftCost() {
        return shiftCost;
    }

    public void setShiftCost(ShiftCost shiftCost) {
        this.shiftCost = shiftCost;
    }

    @SerializedName("shift_cost")
    @Expose

    private ShiftCost shiftCost;

    public FirstOrder getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(FirstOrder firstOrder) {
        this.firstOrder = firstOrder;
    }

    public LastOrder getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(LastOrder lastOrder) {
        this.lastOrder = lastOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

}