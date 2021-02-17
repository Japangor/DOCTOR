package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class TokenComfirmBookingResult {

    @SerializedName("DoctorName") String DoctorName;
    @SerializedName("ImgPath") String ImgPath;
    @SerializedName("PatientName") String PatientName;
    @SerializedName("ContactNumber") String ContactNumber;
    @SerializedName("Email") String Email;
    @SerializedName("DispensaryName") String DispensaryName;
    @SerializedName("PurposeOfVisit") String PurposeOfVisit;
    @SerializedName("TokenNo") String TokenNo;
    @SerializedName("BookingFees") String BookingFees;
    @SerializedName("PaymentGateway") String PaymentGateway;
    @SerializedName("MerchantName") String MerchantName;
    @SerializedName("MerchantLogo") String MerchantLogo;
    @SerializedName("MerchantKey") String MerchantKey;
    @SerializedName("MerchantSecret") String MerchantSecret;

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

    public String getPurposeOfVisit() {
        return PurposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        PurposeOfVisit = purposeOfVisit;
    }

    public String getTokenNo() {
        return TokenNo;
    }

    public void setTokenNo(String tokenNo) {
        TokenNo = tokenNo;
    }

    public String getBookingFees() {
        return BookingFees;
    }

    public void setBookingFees(String bookingFees) {
        BookingFees = bookingFees;
    }

    public String getPaymentGateway() {
        return PaymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        PaymentGateway = paymentGateway;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getMerchantLogo() {
        return MerchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        MerchantLogo = merchantLogo;
    }

    public String getMerchantKey() {
        return MerchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        MerchantKey = merchantKey;
    }

    public String getMerchantSecret() {
        return MerchantSecret;
    }

    public void setMerchantSecret(String merchantSecret) {
        MerchantSecret = merchantSecret;
    }
}
