package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOCategoryQParameter;
import com.whatscover.android.webservice.dto.DTOQuestionParameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 13.05.2017.
 */

public interface QuestionParameterInterface {

    /**
     * Search Function
     * @param query ???
     * @return List<DTOQuestionParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/question-parameters")
    Call<List<DTOQuestionParameter>> searchQuestionParameters(@Query("query") String query);

    /**
     * Get All
     * @return List<DTOQuestionParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/question-parameters")
    Call<List<DTOQuestionParameter>> getQuestionParameters();

    /**
     * Get With Id
     * @param id question_parameter id
     * @return DTOQuestionParameter
     */
    @Headers("Content-Type:application/json")
    @GET("api/question-parameters/{id}")
    Call<DTOQuestionParameter> getQuestionParameterWithID(@Path("id") long id);

}
