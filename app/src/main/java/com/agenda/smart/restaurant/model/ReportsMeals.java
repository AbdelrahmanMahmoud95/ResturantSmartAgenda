package com.agenda.smart.restaurant.model;

/**
 * Created by User on 22/10/2018.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsMeals {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("meals")
    @Expose
    private List<Meal2> meals = null;
    @SerializedName("heading")
    @Expose
    private String heading;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Meal2> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal2> meals) {
        this.meals = meals;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

}
