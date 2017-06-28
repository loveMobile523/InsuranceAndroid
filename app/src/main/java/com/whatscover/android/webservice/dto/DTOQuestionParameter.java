package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOQuestionParameter {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("questionString")
    private String questionString;

    public DTOQuestionParameter() {}

    public DTOQuestionParameter(String name, String type, String questionString) {
        this.name = name;
        this.type = type ;
        this.questionString = questionString;
    }

    public DTOQuestionParameter(Integer id, String name, String type, String questionString) {
        this.id = id;
        this.name = name;
        this.type = type ;
        this.questionString = questionString;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getQuestionString() {
        return questionString;
    }
}

