package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class AppointmentSlotModel {

    @SerializedName("SlotBookingID") String TokenBookingID;
    @SerializedName("BookingDate") String BookingDate;
    @SerializedName("PurposeOfVisit") String PurposeOfVisit;
    @SerializedName("SlotTime") String SlotTime;
    @SerializedName("DispensaryName") String DispensaryName;
    @SerializedName("DispensaryLocation") String DispensaryLocation;
    @SerializedName("DispensaryLat") String DispensaryLat;
    @SerializedName("DispensaryLong") String DispensaryLong;
    @SerializedName("DispensaryTiming") String DispensaryTiming;
    @SerializedName("DoctorName") String DoctorName;
    @SerializedName("PatientID") String PatientID;
    @SerializedName("PatientName") String PatientName;

    public String getTokenBookingID() {
        return TokenBookingID;
    }

    public void setTokenBookingID(String tokenBookingID) {
        TokenBookingID = tokenBookingID;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getPurposeOfVisit() {
        return PurposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        PurposeOfVisit = purposeOfVisit;
    }

    public String getSlotTime() {
        return SlotTime;
    }

    public void setSlotTime(String slotTime) {
        SlotTime = slotTime;
    }

    public String getDispensaryName() {
        return DispensaryName;
    }

    public void setDispensaryName(String dispensaryName) {
        DispensaryName = dispensaryName;
    }

    public String getDispensaryLocation() {
        return DispensaryLocation;
    }

    public void setDispensaryLocation(String dispensaryLocation) {
        DispensaryLocation = dispensaryLocation;
    }

    public String getDispensaryLat() {
        return DispensaryLat;
    }

    public void setDispensaryLat(String dispensaryLat) {
        DispensaryLat = dispensaryLat;
    }

    public String getDispensaryLong() {
        return DispensaryLong;
    }

    public void setDispensaryLong(String dispensaryLong) {
        DispensaryLong = dispensaryLong;
    }

    public String getDispensaryTiming() {
        return DispensaryTiming;
    }

    public void setDispensaryTiming(String dispensaryTiming) {
        DispensaryTiming = dispensaryTiming;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }
}
