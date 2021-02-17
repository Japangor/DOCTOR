package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class DoctorList {

    @SerializedName("DoctorID") String DoctorID;
    @SerializedName("DoctorName") String DoctorName;

    public DoctorList(String doctorID, String doctorName) {
        DoctorID = doctorID;
        DoctorName = doctorName;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }
}
