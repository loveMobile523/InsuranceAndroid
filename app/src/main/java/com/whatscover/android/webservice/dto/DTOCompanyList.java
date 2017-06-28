package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOCompanyList {
    @SerializedName("id")
    private List<DTOCompany> companyList;

    public DTOCompanyList() {}

    public DTOCompanyList(List<DTOCompany> companyList) {
        this.companyList = companyList;
    }

    public void setCompanyList(List<DTOCompany> companyList) {
        this.companyList = companyList;
    }

    public List<DTOCompany> getCompanyList() {
        return companyList;
    }
}
