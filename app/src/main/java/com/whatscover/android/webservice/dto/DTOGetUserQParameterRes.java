package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorin on 15.05.2017.
 */

public class DTOGetUserQParameterRes {
    @SerializedName("user")
    private User user;
    @SerializedName("questionParameterList")
    private List<DTOQuestionParameter> questionParameterList = new ArrayList<>();;

    public DTOGetUserQParameterRes() {}

    public DTOGetUserQParameterRes(User user, List<DTOQuestionParameter> questionParameterList) {
        this.user = user;
        this.questionParameterList = questionParameterList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setQuestionParameterList(List<DTOQuestionParameter> questionParameterList) {
        this.questionParameterList = questionParameterList;
    }

    public List<DTOQuestionParameter> getQuestionParameterList() {
        return questionParameterList;
    }
}
