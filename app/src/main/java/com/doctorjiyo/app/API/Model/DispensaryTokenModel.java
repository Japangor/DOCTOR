package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class DispensaryTokenModel {
    @SerializedName("dispansaryid")
    String dispansaryid;
    @SerializedName("dispansaryname")
    String dispansaryname;
    @SerializedName("TokenNumber")
    String TokenNumber;
    @SerializedName("EstimatedTime")
    String EstimatedTime;
    @SerializedName("location")
    String location;

    public String getTokenNumber() {
        return TokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        TokenNumber = tokenNumber;
    }

    public String getEstimatedTime() {
        return EstimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        EstimatedTime = estimatedTime;
    }

    public String getDispansaryid() {
        return dispansaryid;
    }

    public void setDispansaryid(String dispansaryid) {
        this.dispansaryid = dispansaryid;
    }

    public String getDispansaryname() {
        return dispansaryname;
    }

    public void setDispansaryname(String dispansaryname) {
        this.dispansaryname = dispansaryname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
