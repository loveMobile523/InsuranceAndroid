package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 20.05.2017.
 */

public class DTOApplication {
    @SerializedName("id")
    private Integer id;
    @SerializedName("note")
    private String note;
    @SerializedName("dateApplied")
    private String dateApplied;
    @SerializedName("status")
    private String status;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("userLogin")
    private String userLogin;
    @SerializedName("agentId")
    private Integer agentId;
    @SerializedName("agentLogin")
    private String agentLogin;
    @SerializedName("activeProductId")
    private Integer activeProductId;
    @SerializedName("activeProductTitle")
    private String activeProductTitle;

    public DTOApplication() {}

    public DTOApplication(String note, String dateApplied, String status,
                          Integer userId, String userLogin, Integer agentId, String agentLogin,
                          Integer activeProductId, String activeProductTitle) {
        this.note = note;
        this.dateApplied = dateApplied;
        this.status = status;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
        this.activeProductId = activeProductId;
        this.activeProductTitle = activeProductTitle;
    }

    public DTOApplication(Integer id, String note, String dateApplied, String status,
                          Integer userId, String userLogin, Integer agentId, String agentLogin,
                          Integer activeProductId, String activeProductTitle) {
        this.id = id;
        this.note = note;
        this.dateApplied = dateApplied;
        this.status = status;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
        this.activeProductId = activeProductId;
        this.activeProductTitle = activeProductTitle;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentLogin(String agentLogin) {
        this.agentLogin = agentLogin;
    }

    public String getAgentLogin() {
        return agentLogin;
    }

    public void setActiveProductId(Integer activeProductId) {
        this.activeProductId = activeProductId;
    }

    public Integer getActiveProductId() {
        return activeProductId;
    }

    public void setActiveProductTitle(String activeProductTitle) {
        this.activeProductTitle = activeProductTitle;
    }

    public String getActiveProductTitle() {
        return activeProductTitle;
    }
}
