package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOUserQParameter {
    @SerializedName("id")
    private Integer id;
    @SerializedName("value")
    private String value;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("questionParameterId")
    private Integer questionParameterId;

    @SerializedName("userLogin")
    private String userLogin;

    public DTOUserQParameter() {}

    public DTOUserQParameter(String value, Integer userId, Integer questionParameterId, String userLogin) {
        this.value = value;
        this.userId = userId;
        this.questionParameterId = questionParameterId;

        this.userLogin = userLogin;
    }

    public DTOUserQParameter(Integer id, String value, Integer userId, Integer questionParameterId, String userLogin) {
        this.id = id;
        this.value = value;
        this.userId = userId;
        this.questionParameterId = questionParameterId;

        this.userLogin = userLogin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setQuestionParameterId(Integer questionParameterId) {
        this.questionParameterId = questionParameterId;
    }

    public Integer getQuestionParameterId() {
        return questionParameterId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }
}

