package com.doctorjiyo.app.Doctor.API.Model;

import com.google.gson.annotations.SerializedName;

public class PatientList {
    @SerializedName("PatientID")
    String PatinetID;
    @SerializedName("PatientName")
    String PatientName;
    @SerializedName("PatientPhoto")
    String PatientPhoto;

    public String getPatinetID() {
        return PatinetID;
    }

    public void setPatinetID(String patinetID) {
        PatinetID = patinetID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientPhoto() {
        return PatientPhoto;
    }

    public void setPatientPhoto(String patientPhoto) {
        PatientPhoto = patientPhoto;
    }
}
