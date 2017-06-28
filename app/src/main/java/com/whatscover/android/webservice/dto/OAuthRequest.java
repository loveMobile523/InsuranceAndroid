package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 02.05.2017.
 */

public class OAuthRequest {

    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("rememberMe")
    private boolean rememberMe;

    /**
     * @param userName
     * @param password
     */
    public OAuthRequest(String userName, String password, boolean rememberMe) {

        this.userName = userName;
        this.password = password;
        this.rememberMe = rememberMe;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
}
