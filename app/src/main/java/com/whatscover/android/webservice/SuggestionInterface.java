package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.AddSuggestionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by dorin on 03.05.2017.
 */

public interface SuggestionInterface {

    /**
     *
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("api/product/suggestion")
    Call<AddSuggestionResponse> addSuggestion();
}
