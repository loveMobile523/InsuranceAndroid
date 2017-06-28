package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOCategoryQParameter;
import com.whatscover.android.webservice.dto.DTOQuestionParameter;
import com.whatscover.android.webservice.dto.DTOUserQParameter;
import com.whatscover.android.webservice.dto.DTOUserQParameterNoId;
import com.whatscover.android.webservice.dto.DTOUserQParameterNoIdList;
import com.whatscover.android.webservice.dto.RegisterRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 13.05.2017.
 */

public interface UserQParameterInterface {

    /**
     * Search Function
     * @param query ??
     * @return List<DTOUserQParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/user-question-parameter-values")
    Call<List<DTOUserQParameter>> searchUserQParameters(@Query("query") String query);

    /**
     * Get All
     * @return List<DTOUserQParameter>
     */
    @Headers("Content-Type:application/json")
    @GET("api/user-question-parameter-values")
    Call<List<DTOUserQParameter>> getUserQParameters();

    /**
     * Get With Id
     * @param id user_question_parameter_value id
     * @return DTOUserQParameter
     */
    @Headers("Content-Type:application/json")
    @GET("api/user-question-parameter-values/{id}")
    Call<DTOUserQParameter> getUserQParameterWithID(@Path("id") long id);

    // ---------------------------------------------------------------------------------------------

    /**
     * save user question parameter value
     * @param userQuestionParameterValueListDTO userQuestionParameterValueListDTO
     * @return List<DTOUserQParameter>
     */
    @Headers("Content-Type:application/json")
    @POST("api/user-question-parameter-values/save-bulk")
    Call<List<DTOUserQParameter>> saveBulkUserQuestionParameterValue(@Body DTOUserQParameterNoIdList userQuestionParameterValueListDTO);
}
