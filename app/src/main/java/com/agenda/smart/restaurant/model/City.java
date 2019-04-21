package com.agenda.smart.restaurant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abdelrahman on 7/8/2018.
 */

public class City {

    @SerializedName("CityUID")
    @Expose
    private int cityUID;
    @SerializedName("Name")
    @Expose
    private String name;

    public int getCityUID() {
        return cityUID;
    }

    public void setCityUID(int cityUID) {
        this.cityUID = cityUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
