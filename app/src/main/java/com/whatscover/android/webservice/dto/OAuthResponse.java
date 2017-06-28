package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 02.05.2017.
 */

public class OAuthResponse {
    @SerializedName("id_token")
    private String idToken;

    public OAuthResponse() {    }

    public OAuthResponse(String idToken) {
        this.idToken = idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }
}
