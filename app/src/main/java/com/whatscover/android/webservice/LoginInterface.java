package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.OAuthRequest;
import com.whatscover.android.webservice.dto.OAuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by dorin on 05.05.2017.
 */

public interface LoginInterface {

    // OAuthInterface
    /**
     * The method to obtain the oauth access token.
     * @param request The request
     * @return OAuthResponse token
     * @see OAuthRequest
     * @see OAuthResponse
     */
    @Headers("Content-Type:application/json")
    @POST("api/authenticate")
    Call<OAuthResponse> getAccessToken(@Body OAuthRequest request);
}
