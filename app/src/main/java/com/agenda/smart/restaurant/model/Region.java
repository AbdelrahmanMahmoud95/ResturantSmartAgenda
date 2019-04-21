package com.agenda.smart.restaurant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abdelrahman on 7/12/2018.
 */

public class Region {
    @SerializedName("DistrictUID")
    @Expose
    private int districtUID;
    @SerializedName("Name")
    @Expose
    private String name;

    public int getDistrictUID() {
        return districtUID;
    }

    public void setDistrictUID(int districtUID) {
        this.districtUID = districtUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}