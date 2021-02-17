package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseDoctorProfile {
    @SerializedName("success")    private Boolean success;
    @SerializedName("message")    private String message;
    @SerializedName("DoctorID") private String DoctorID;
    @SerializedName("ImgPath") private String ImgPath;
    @SerializedName("DoctorName") private String DoctorName;
    @SerializedName("Qualification") private String Qualification;
    @SerializedName("Experience") private String Experience;
    @SerializedName("Specaility") private String Specaility;
    @SerializedName("Location") private String Location;
    @SerializedName("Knowledge") private String Knowledge;


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

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSpecaility() {
        return Specaility;
    }

    public void setSpecaility(String specaility) {
        Specaility = specaility;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getKnowledge() {
        return Knowledge;
    }

    public void setKnowledge(String knowledge) {
        Knowledge = knowledge;
    }
}
