package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorin on 13.05.2017.
 */

public class DTOCategoryList {
    @SerializedName("data")
    private List<DTOCategory> categoryList;

    public DTOCategoryList() {}

    public DTOCategoryList(List<DTOCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public void setCategoryList(List<DTOCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<DTOCategory> getCategoryList() {
        return categoryList;
    }
}
