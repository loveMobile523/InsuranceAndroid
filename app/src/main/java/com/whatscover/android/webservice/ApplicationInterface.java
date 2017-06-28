package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOApplication;
import com.whatscover.android.webservice.dto.DTOCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 20.05.2017.
 */

public interface ApplicationInterface {

    /**
     * Search Function
     * @param query application Title // ??
     * @param page page
     * @param size size
     * @param sort sort
     * @return List<DTOApplication>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/applications")
    Call<List<DTOApplication>> searchApplications(@Query("page") int page, @Query("size") int size,
                                                  @Query("query") String query, @Query("sort") String[] sort);

    /**
     * Get All
     * @param page page
     * @param size size
     * @param sort sort
     * @return List<DTOApplication>
     */
    @Headers("Content-Type:application/json")
    @GET("api/applications")
    Call<List<DTOApplication>> getAllApplications(@Query("page") int page, @Query("size") int size,
                                                  @Query("sort") String[] sort);

    /**
     * Get With ID
     * @param id application id
     * @return DTOApplication
     */
    @Headers("Content-Type:application/json")
    @GET("api/applications/{id}")
    Call<DTOApplication> getApplicationsWithID(@Path("id") long id);

}
