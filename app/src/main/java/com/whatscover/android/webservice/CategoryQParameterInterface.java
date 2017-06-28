package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategoryQParameter;
import com.whatscover.android.webservice.dto.DTOGetUserQParameterRes;

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

public interface CategoryQParameterInterface {

    /**
     * Search Function
     * @param query category id
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/category-question-parameters")
    Call<List<DTOCategoryQParameter>> searchCategorieQParameters(@Query("query") String query);

    /**
     * Get All
     * @return List<DTOCategoryQParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/category-question-parameters")
    Call<List<DTOCategoryQParameter>> getCategoryQParameters();

    /**
     * Get With id
     * @param id category_question_parameter id
     * @return DTOCategoryQParameter
     */
    @Headers("Content-Type:application/json")
    @GET("api/category-question-parameters/{id}")
    Call<DTOCategoryQParameter> getCategoryQParameterWithID(@Path("id") long id);

    // ---------------------------------------------------------------------------------------------
    // first request url

    /**
     * Get User Question Parameters
     * @param categoryId categoryId
     * @return DTOGetUserQParameterRes
     */
    @Headers("Content-Type:application/json")
    @GET("api/category-question-parameters/category/{categoryId}")
    Call<DTOGetUserQParameterRes> getUserQuestionParametes(@Path("categoryId") long categoryId);
}
