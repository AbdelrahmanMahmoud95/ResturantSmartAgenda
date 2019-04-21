package com.agenda.smart.restaurant.model;

/**
 * Created by User on 23/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTax {



        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("order_id")
        @Expose
        private int orderId;
        @SerializedName("meal_id")
        @Expose
        private int mealId;
        @SerializedName("cost")
        @Expose
        private double cost;
        @SerializedName("size")
        @Expose
        private String size;
        @SerializedName("quantity")
        @Expose
        private int quantity;
        @SerializedName("printed")
        @Expose
        private int printed;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getMealId() {
            return mealId;
        }

        public void setMealId(int mealId) {
            this.mealId = mealId;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrinted() {
            return printed;
        }

        public void setPrinted(int printed) {
            this.printed = printed;
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


