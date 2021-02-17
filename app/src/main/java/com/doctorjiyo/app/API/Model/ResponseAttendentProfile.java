package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseAttendentProfile {
    @SerializedName("success")    private Boolean success;
    @SerializedName("message")    private String message;
    @SerializedName("AttendantID") private String AttendantID;
    @SerializedName("ImgPath") private String ImgPath;
    @SerializedName("AttendantName") private String AttendantName;
    @SerializedName("Qualification") private String Qualification;
    @SerializedName("Experience") private String Experience;
    @SerializedName("Specaility") private String Specaility;
    @SerializedName("Location") private String Location;
    @SerializedName("Knowledge") private String Knowledge;

    public ResponseAttendentProfile(Boolean success, String message, String attendantID, String imgPath, String attendantName, String qualification, String experience, String specaility, String location, String knowledge) {
        this.success = success;
        this.message = message;
        AttendantID = attendantID;
        ImgPath = imgPath;
        AttendantName = attendantName;
        Qualification = qualification;
        Experience = experience;
        Specaility = specaility;
        Location = location;
        Knowledge = knowledge;
    }

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

    public String getAttendantID() {
        return AttendantID;
    }

    public void setAttendantID(String attendantID) {
        AttendantID = attendantID;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getAttendantName() {
        return AttendantName;
    }

    public void setAttendantName(String attendantName) {
        AttendantName = attendantName;
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
