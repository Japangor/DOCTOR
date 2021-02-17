package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class ResultLoginResponse {


    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("DoctorID")
    private String DoctorID;

    @SerializedName("AttendantID")
    private String AttendantID;

    @SerializedName("PatientID")
    private String PatientID;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public String getAttendantID() {
        return AttendantID;
    }

    public void setAttendantID(String attendantID) {
        AttendantID = attendantID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }
}
