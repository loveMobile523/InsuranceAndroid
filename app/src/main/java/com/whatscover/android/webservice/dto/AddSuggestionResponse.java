package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorin on 03.05.2017.
 */

public class AddSuggestionResponse {
    @SerializedName("userDTO")
    private User user;

    @SerializedName("productSuggestionDTOList")
    private List<Object> productSuggestionDTOList = new ArrayList<>();

    public AddSuggestionResponse() {}

    public void setProductSuggestionDTOList(List<Object> productSuggestionDTOList) {
        this.productSuggestionDTOList = productSuggestionDTOList;
    }

    public List<Object> getProductSuggestionDTOList() {
        return productSuggestionDTOList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
