package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class DispensaryModel {

    @SerializedName("dispansaryid")
    String dispansaryid;
    @SerializedName("dispansaryname")
    String dispansaryname;
    @SerializedName("location")
    String location;

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