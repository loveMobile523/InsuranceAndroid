package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.RegisterRequest;
import com.whatscover.android.webservice.dto.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by dorin on 05.05.2017.
 */

public interface RegisterInterface {

    /**
     * The method to register new user.
     * @param request The request
     * @return User Info
     * @see RegisterRequest
     * @see RegisterResponse
     */
    @Headers("Content-Type:application/json")
    @POST("api/register")
    Call<Void> registerUser(@Body RegisterRequest request);
}
