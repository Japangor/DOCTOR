package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class PrescriptionModel {
    @SerializedName("PrescriptionID")
    String PrescriptionID;
    @SerializedName("PrescriptionTitle")
    String PrescriptionTitle;
    @SerializedName("PatientName")
    String PatientName;
    @SerializedName("DoctorName")
    String DoctorName;
    @SerializedName("PrescriptionDate")
    String PrescriptionDate;
    @SerializedName("Diagnosis")
    String Diagnosis;

    public String getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    public String getPrescriptionTitle() {
        return PrescriptionTitle;
    }

    public void setPrescriptionTitle(String prescriptionTitle) {
        PrescriptionTitle = prescriptionTitle;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getPrescriptionDate() {
        return PrescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        PrescriptionDate = prescriptionDate;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }
}