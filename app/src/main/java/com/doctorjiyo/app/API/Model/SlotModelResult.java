package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlotModelResult {
    @SerializedName("DispensaryID") String DispensaryID;
    @SerializedName("DoctorID") String DoctorID;
    @SerializedName("DispensaryTiming") String DispensaryTiming;
    @SerializedName("TimeFrom") String TimeFrom;
    @SerializedName("TimeTo") String TimeTo;
    @SerializedName("SlotTimes") List<SlotTimesModel> SlotTimes;

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

    public List<SlotTimesModel> getSlotTimes() {
        return SlotTimes;
    }

    public void setSlotTimes(List<SlotTimesModel> slotTimes) {
        SlotTimes = slotTimes;
    }
}
