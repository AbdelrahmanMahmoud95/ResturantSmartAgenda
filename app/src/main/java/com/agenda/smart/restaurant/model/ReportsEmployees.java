package com.agenda.smart.restaurant.model;

/**
 * Created by User on 24/10/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsEmployees {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("employees")
    @Expose
    private List<Employee> employees = null;

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
