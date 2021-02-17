package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class SlotTimesModel {

    @SerializedName("SlotTime") String SlotTime;
    @SerializedName("AvailableSlot") String AvailableSlot;

    public String getSlotTime() {
        return SlotTime;
    }

    public void setSlotTime(String slotTime) {
        SlotTime = slotTime;
    }

    public String getAvailableSlot() {
        return AvailableSlot;
    }

    public void setAvailableSlot(String availableSlot) {
        AvailableSlot = availableSlot;
    }
}
