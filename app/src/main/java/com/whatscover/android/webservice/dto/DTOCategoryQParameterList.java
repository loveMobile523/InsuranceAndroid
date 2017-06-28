package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOCategoryQParameterList {
    @SerializedName("data")
    private List<DTOCategoryQParameter> categoryQParameterList;

    public DTOCategoryQParameterList() {}

    public DTOCategoryQParameterList(List<DTOCategoryQParameter> categoryQParameterList) {
        this.categoryQParameterList = categoryQParameterList;
    }

    public void setCategoryQParameterList(List<DTOCategoryQParameter> categoryQParameterList) {
        this.categoryQParameterList = categoryQParameterList;
    }

    public List<DTOCategoryQParameter> getCategoryQParameterList() {
        return categoryQParameterList;
    }
}

