package com.whatscover.android.webservice.dto;

import com.google.common.cache.Weigher;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 22.05.2017.
 */

public class DTOApplyProductResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("applyDate")
    private String applyDate;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("userLogin")
    private String userLogin;
    @SerializedName("agentId")
    private Integer agentId;
    @SerializedName("agentLogin")
    private String agentLogin;
    @SerializedName("productId")
    private Integer productId;

    public DTOApplyProductResponse() {}

    public DTOApplyProductResponse(String applyDate, Integer userId, String userLogin,
                                   Integer agentId, String agentLogin, Integer productId) {
        this.applyDate = applyDate;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
        this.productId = productId;
    }

    public DTOApplyProductResponse(Integer id, String applyDate, Integer userId, String userLogin,
                                   Integer agentId, String agentLogin, Integer productId) {
        this.id = id;
        this.applyDate = applyDate;
        this.userId = userId;
        this.userLogin = userLogin;
        this.agentId = agentId;
        this.agentLogin = agentLogin;
        this.productId = productId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentLogin(String agentLogin) {
        this.agentLogin = agentLogin;
    }

    public String getAgentLogin() {
        return agentLogin;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }
}
