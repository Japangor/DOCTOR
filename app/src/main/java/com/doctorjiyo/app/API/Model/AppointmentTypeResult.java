package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class AppointmentTypeResult {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("AppointmentType")
    private String AppointmentType;

    public AppointmentTypeResult(Boolean success, String appointmentType) {
        this.success = success;
        AppointmentType = appointmentType;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAppointmentType() {
        return AppointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        AppointmentType = appointmentType;
    }
}
