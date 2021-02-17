package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class TokenBookingResult {

    @SerializedName("DoctorName") String DoctorName;
    @SerializedName("ImgPath") String ImgPath;
    @SerializedName("PatientName") String PatientName;
    @SerializedName("ContactNumber") String ContactNumber;
    @SerializedName("Email") String Email;
    @SerializedName("DispensaryName") String DispensaryName;
    @SerializedName("BookingFees") String BookingFees;

    public TokenBookingResult(String doctorName, String imgPath, String patientName, String contactNumber, String email, String dispensaryName, String bookingFees) {
        DoctorName = doctorName;
        ImgPath = imgPath;
        PatientName = patientName;
        ContactNumber = contactNumber;
        Email = email;
        DispensaryName = dispensaryName;
        BookingFees = bookingFees;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDispensaryName() {
        return DispensaryName;
    }

    public void setDispensaryName(String dispensaryName) {
        DispensaryName = dispensaryName;
    }

    public String getBookingFees() {
        return BookingFees;
    }

    public void setBookingFees(String bookingFees) {
        BookingFees = bookingFees;
    }
}
