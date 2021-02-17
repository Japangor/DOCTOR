package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppointmentListModel {


    @SerializedName("Tokens") List<AppointmentTokenModel> Tokens;
    @SerializedName("Slots") List<AppointmentSlotModel> Slots;

    public List<AppointmentTokenModel> getTokens() {
        return Tokens;
    }

    public void setTokens(List<AppointmentTokenModel> tokens) {
        Tokens = tokens;
    }

    public List<AppointmentSlotModel> getSlots() {
        return Slots;
    }

    public void setSlots(List<AppointmentSlotModel> slots) {
        Slots = slots;
    }
}
