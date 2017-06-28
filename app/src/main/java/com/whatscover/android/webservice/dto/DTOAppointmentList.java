package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dorin on 21.05.2017.
 */

public class DTOAppointmentList {
    @SerializedName("data")
    private List<DTOAppointment> appointmentList;

    public DTOAppointmentList() {}

    public DTOAppointmentList(List<DTOAppointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public void setAppointmentList(List<DTOAppointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<DTOAppointment> getAppointmentList() {
        return appointmentList;
    }
}
