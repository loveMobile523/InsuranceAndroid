package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOUserQParameterNoIdList {
    @SerializedName("userQuestionParameterValueDTOList")
    private List<DTOUserQParameterNoId> dtoUserQParameterNoIds;

    public DTOUserQParameterNoIdList() {}

    public DTOUserQParameterNoIdList(List<DTOUserQParameterNoId> dtoUserQParameterNoIds) {
        this.dtoUserQParameterNoIds = dtoUserQParameterNoIds;
    }

    public void setDtoUserQParameterNoIds(List<DTOUserQParameterNoId> dtoUserQParameterNoIds) {
        this.dtoUserQParameterNoIds = dtoUserQParameterNoIds;
    }

    public List<DTOUserQParameterNoId> getDtoUserQParameterNoIds() {
        return dtoUserQParameterNoIds;
    }
}

