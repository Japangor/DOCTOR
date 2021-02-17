package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class TokenModelResult {


    @SerializedName("DispensaryID") String DispensaryID;
    @SerializedName("DoctorID") String DoctorID;
    @SerializedName("DispensaryTiming") String DispensaryTiming;
    @SerializedName("TimeFrom") String TimeFrom;
    @SerializedName("TimeTo") String TimeTo;
    @SerializedName("TokenNo") String TokenNo;
    @SerializedName("EstimateTime") String EstimateTime;


    public TokenModelResult(String dispensaryID, String doctorID, String dispensaryTiming, String timeFrom, String timeTo, String tokenNo, String estimateTime) {
        DispensaryID = dispensaryID;
        DoctorID = doctorID;
        DispensaryTiming = dispensaryTiming;
        TimeFrom = timeFrom;
        TimeTo = timeTo;
        TokenNo = tokenNo;
        EstimateTime = estimateTime;
    }

    public String getDispensaryID() {
        return DispensaryID;
    }

    public void setDispensaryID(String dispensaryID) {
        DispensaryID = dispensaryID;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public String getDispensaryTiming() {
        return DispensaryTiming;
    }

    public void setDispensaryTiming(String dispensaryTiming) {
        DispensaryTiming = dispensaryTiming;
    }

    public String getTimeFrom() {
        return TimeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        TimeFrom = timeFrom;
    }

    public String getTimeTo() {
        return TimeTo;
    }

    public void setTimeTo(String timeTo) {
        TimeTo = timeTo;
    }

    public String getTokenNo() {
        return TokenNo;
    }

    public void setTokenNo(String tokenNo) {
        TokenNo = tokenNo;
    }

    public String getEstimateTime() {
        return EstimateTime;
    }

    public void setEstimateTime(String estimateTime) {
        EstimateTime = estimateTime;
    }
}


