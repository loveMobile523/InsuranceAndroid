package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOProductQParameterList {
    @SerializedName("data")
    private List<DTOProductQParameter> productQParameters;

    public DTOProductQParameterList() {}

    public DTOProductQParameterList(List<DTOProductQParameter> productQParameters) {
        this.productQParameters = productQParameters;
    }

    public void setProductQParameters(List<DTOProductQParameter> productQParameters) {
        this.productQParameters = productQParameters;
    }

    public List<DTOProductQParameter> getProductQParameters() {
        return productQParameters;
    }
}

