package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOProductQParameter {
    @SerializedName("id")
    private Integer id;
    @SerializedName("value")
    private String value;
    @SerializedName("relevanceFactor")
    private String relevanceFactor;
    @SerializedName("importanceValue")
    private int importanceValue;
    @SerializedName("productId")
    private Integer productId;
    @SerializedName("questionParameterId")
    private Integer questionParameterId;

    public DTOProductQParameter() {}

    public DTOProductQParameter(String value, String relevanceFactor, int importanceValue, Integer productId, Integer questionParameterId) {
        this.value = value;
        this.relevanceFactor = relevanceFactor;
        this.importanceValue = importanceValue;
        this.productId = productId;
        this.questionParameterId = questionParameterId;
    }

    public DTOProductQParameter(Integer id, String value, String relevanceFactor, int importanceValue, Integer productId, Integer questionParameterId) {
        this.id = id;
        this.value = value;
        this.relevanceFactor = relevanceFactor;
        this.importanceValue = importanceValue;
        this.productId = productId;
        this.questionParameterId = questionParameterId;
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

    public void setRelevanceFactor(String relevanceFactor) {
        this.relevanceFactor = relevanceFactor;
    }

    public String getRelevanceFactor() {
        return relevanceFactor;
    }

    public void setImportanceValue(int importanceValue) {
        this.importanceValue = importanceValue;
    }

    public int getImportanceValue() {
        return importanceValue;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setQuestionParameterId(Integer questionParameterId) {
        this.questionParameterId = questionParameterId;
    }

    public Integer getQuestionParameterId() {
        return questionParameterId;
    }
}

