package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 15.05.2017.
 */

public class DTOProductList {
    @SerializedName("data")
    private List<DTOProduct> productList;

    public DTOProductList() {}

    public DTOProductList(List<DTOProduct> productList) {
        this.productList = productList;
    }

    public void setProductList(List<DTOProduct> productList) {
        this.productList = productList;
    }

    public List<DTOProduct> getProductList() {
        return productList;
    }
}
