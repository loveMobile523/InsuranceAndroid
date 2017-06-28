package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOProductQParameter;
import com.whatscover.android.webservice.dto.DTOQuestionParameter;
import com.whatscover.android.webservice.dto.DTOUserQParameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 13.05.2017.
 */

public interface ProductQParameterInterface {

    /**
     * Search Function
     * @param query ??
     * @return List<DTOProductQParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/product-question-parameter-values")
    Call<List<DTOProductQParameter>> searchProductQParameters(@Query("query") String query);

    /**
     * Get All
     * @return List<DTOProductQParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/product-question-parameter-values")
    Call<List<DTOProductQParameter>> getProductQParameters();

    /**
     * Get With Id
     * @param id product_question_parameter_value id
     * @return DTOProductQParameter
     */
    @Headers("Content-Type:application/json")
    @GET("api/product-question-parameter-values/{id}")
    Call<DTOProductQParameter> getProductQParameterWithID(@Path("id") long id);
}
