package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by dorin on 16.05.2017.
 */

public interface UserInterface {

    /**
     * Get User
     * @param login login
     * @return User
     */
    @Headers("Content-Type:application/json")
    @GET("api/users/{login}")
    Call<User> getUser(@Path("login") String login);
}
