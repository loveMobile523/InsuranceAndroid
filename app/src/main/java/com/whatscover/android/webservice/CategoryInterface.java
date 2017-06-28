package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.AddSuggestionResponse;
import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOCategoryList;
import com.whatscover.android.webservice.dto.DTOUserQParameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 13.05.2017.
 */

public interface CategoryInterface {

    /**
     * Search Function
     * @param query Category Title
     * @return List<DTOCategory>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/categories")
    Call<List<DTOCategory>> searchCategories(@Query("query") String query);

    /**
     * Get All
     * @return List<DTOCategory>
     */
    @Headers("Content-Type:application/json")
    @GET("api/categories")
    Call<List<DTOCategory>> getCategories();

//    @Headers("Content-Type:application/json")
//    @GET("api/categories")
//    Call<DTOCategoryList> getAllCategories();

    @Headers("Content-Type:application/json")
    @GET("api/categories")
    void getCategories(Callback<List<DTOCategory>> categoriesDTOCallback);

    /**
     * Get With ID
     * @param id category id
     * @return DTOCategory
     */
    @Headers("Content-Type:application/json")
    @GET("api/categories/{id}")
    Call<DTOCategory> getCategoryWithID(@Path("id") long id);

}
