package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOQuestionParameterList {
    @SerializedName("data")
    private List<DTOQuestionParameter> questionParameterList;

    public DTOQuestionParameterList() {}

    public DTOQuestionParameterList(List<DTOQuestionParameter> questionParameterList) {
        this.questionParameterList = questionParameterList;
    }

    public void setQuestionParameterList(List<DTOQuestionParameter> questionParameterList) {
        this.questionParameterList = questionParameterList;
    }

    public List<DTOQuestionParameter> getQuestionParameterList() {
        return questionParameterList;
    }
}

