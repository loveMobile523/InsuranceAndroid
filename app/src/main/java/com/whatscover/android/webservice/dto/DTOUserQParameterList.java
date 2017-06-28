package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOUserQParameterList {
    @SerializedName("data")
    private List<DTOUserQParameter> userQParameterList;

    public DTOUserQParameterList() {}

    public DTOUserQParameterList(List<DTOUserQParameter> userQParameterList) {
        this.userQParameterList = userQParameterList;
    }

    public void setUserQParameterList(List<DTOUserQParameter> userQParameterList) {
        this.userQParameterList = userQParameterList;
    }

    public List<DTOUserQParameter> getUserQParameterList() {
        return userQParameterList;
    }
}

