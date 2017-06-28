package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOApplication;
import com.whatscover.android.webservice.dto.DTOAppointment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 21.05.2017.
 */

public interface AppointmentInterface {

    /**
     * Search Function
     * @param query Appointment Title // ??
     * @param page page
     * @param size size
     * @param sort sort
     * @return List<DTOAppointment>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/appointments")
    Call<List<DTOAppointment>> searchAppointments(@Query("page") int page, @Query("size") int size,
                                                  @Query("query") String query, @Query("sort") String[] sort);

    /**
     * Get All
     * @param page page
     * @param size size
     * @param sort sort
     * @return List<DTOAppointment>
     */
    @Headers("Content-Type:application/json")
    @GET("api/appointments")
    Call<List<DTOAppointment>> getAllAppointments(@Query("page") int page, @Query("size") int size,
                                                  @Query("sort") String[] sort);

    /**
     * Get With ID
     * @param id application id
     * @return DTOApplication
     */
    @Headers("Content-Type:application/json")
    @GET("api/appointments/{id}")
    Call<DTOAppointment> getAppointmentsWithID(@Path("id") long id);
}
