package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 21.05.2017.
 */

public class DTOAppointment {
    @SerializedName("id")
    private Integer id;
    @SerializedName("dueDate")
    private String dueDate;
    @SerializedName("lang")
    private double lang;
    @SerializedName("lot")
    private double lot;
    @SerializedName("status")
    private String status;
    @SerializedName("note")
    private String note;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("userLogin")
    private String userLogin;
    @SerializedName("agentId")
    private Integer agentId;
    @SerializedName("agentLogin")
    private String agentLogin;

    public DTOAppointment() {}

    public DTOAppointment(String dueDate, double lang, double lot, String status,
                          String note, Integer userId, String userLogin, Integer agentId, String agentLogin) {
        this.dueDate = dueDate;
        this.lang = lang;
        this.lot = lot;
        this.status = status;
        this.note = note;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
    }

    public DTOAppointment(Integer id, String dueDate, double lang, double lot, String status,
                          String note, Integer userId, String userLogin, Integer agentId, String agentLogin) {
        this.id = id;
        this.dueDate = dueDate;
        this.lang = lang;
        this.lot = lot;
        this.status = status;
        this.note = note;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLang() {
        return lang;
    }

    public void setLot(double lot) {
        this.lot = lot;
    }

    public double getLot() {
        return lot;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
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
}
