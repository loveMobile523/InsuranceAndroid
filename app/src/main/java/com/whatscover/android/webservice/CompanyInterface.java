package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOCompany;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 14.05.2017.
 */

public interface CompanyInterface {

    /**
     * Search Function
     * @param query ??
     * @return List<DTOCompany>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/companies")
    Call<List<DTOCompany>> searchCompanies(@Query("page") int page, @Query("size") int size,
                                           @Query("query") String query, @Query("sort") String[] sort);

    /**
     * Get All
     * @return List<DTOCompany>
     */
    @Headers("Content-Type:application/json")
    @GET("api/companies")
    Call<List<DTOCompany>> getCompanies(@Query("page") int page, @Query("size") int size,
                                        @Query("sort") String[] sort);

    /**
     * Get With ID
     * @param id company id
     * @return DTOCompany
     */
    @Headers("Content-Type:application/json")
    @GET("api/companies/{id}")
    Call<DTOCompany> getCompanyWithID(@Path("id") long id);

}
