package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class PatientModel {

    @SerializedName("ContactNumber")
    private String ContactNumber;

    @SerializedName("Email")
    private String Email;

    @SerializedName("Password")
    private String Password;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
