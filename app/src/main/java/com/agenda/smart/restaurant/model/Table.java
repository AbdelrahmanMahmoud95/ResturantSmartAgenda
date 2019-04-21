package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 8/5/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("table_image")
    @Expose
    private String tableImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table withId(int id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Table withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTableImage() {
        return tableImage;
    }

    public void setTableImage(String tableImage) {
        this.tableImage = tableImage;
    }

    public Table withTableImage(String tableImage) {
        this.tableImage = tableImage;
        return this;
    }


}
