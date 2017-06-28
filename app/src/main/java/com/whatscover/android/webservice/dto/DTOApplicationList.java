package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 20.05.2017.
 */

public class DTOApplicationList {
    @SerializedName("data")
    private List<DTOApplication> applicationList;

    public DTOApplicationList() {}

    public DTOApplicationList(List<DTOApplication> applicationList) {
        this.applicationList = applicationList;
    }

    public void setApplicationList(List<DTOApplication> applicationList) {
        this.applicationList = applicationList;
    }

    public List<DTOApplication> getApplicationList() {
        return applicationList;
    }
}
