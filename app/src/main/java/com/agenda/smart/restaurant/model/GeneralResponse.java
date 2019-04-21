package com.agenda.smart.restaurant.model;

/**
 * Created by Abdelrahman on 8/5/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralResponse {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("messages")
    @Expose
    private List<String> messages = null;

    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @SerializedName("waiter_id")
    @Expose
    private int waiterId;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @SerializedName("admin_id")
    @Expose

    private int adminId;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

}