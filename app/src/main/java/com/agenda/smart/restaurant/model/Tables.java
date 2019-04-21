package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 8/5/2018.
 */



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tables {

    @SerializedName("tables")
    @Expose
    private List<Table> tables = null;

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

}