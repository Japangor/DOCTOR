package com.doctorjiyo.app.Doctor.API.Model;

import com.google.gson.annotations.SerializedName;

public class AppointmentList {

    @SerializedName("appointmentid") String appointmentid;
    @SerializedName("doctorid") String doctorid;
    @SerializedName("patientid") String patientid;
    @SerializedName("patientname") String patientname;
    @SerializedName("patientcontact") String patientcontact;
    @SerializedName("patientemail") String patientemail;
    @SerializedName("date") String date;
    @SerializedName("doctorname") String doctorname;
    @SerializedName("purpose") String purpose;
    @SerializedName("status") String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(String appointmentid) {
        this.appointmentid = appointmentid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientcontact() {
        return patientcontact;
    }

    public void setPatientcontact(String patientcontact) {
        this.patientcontact = patientcontact;
    }

    public String getPatientemail() {
        return patientemail;
    }

    public void setPatientemail(String patientemail) {
        this.patientemail = patientemail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
