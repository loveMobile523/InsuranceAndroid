package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOCategoryQParameter {
    @SerializedName("id")
    private Integer id;
    @SerializedName("categoryId")
    private Integer categoryId;
    @SerializedName("questionParameterId")
    private Integer questionParameterId;

    public DTOCategoryQParameter() {}

    public DTOCategoryQParameter(Integer categoryId, Integer questionParameterId) {
        this.categoryId = categoryId;
        this.questionParameterId = questionParameterId;
    }

    public DTOCategoryQParameter(Integer id, Integer categoryId, Integer questionParameterId) {
        this.id = id;
        this.categoryId = categoryId;
        this.questionParameterId = questionParameterId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setQuestionParameterId(Integer questionParameterId) {
        this.questionParameterId = questionParameterId;
    }

    public Integer getQuestionParameterId() {
        return questionParameterId;
    }
}

