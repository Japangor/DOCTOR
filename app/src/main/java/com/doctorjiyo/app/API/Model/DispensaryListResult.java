package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DispensaryListResult {

    @SerializedName("DispensaryID") String DispensaryID;
    @SerializedName("DispensaryName") String DispensaryName;
    @SerializedName("DispensaryLocation") String DispensaryLocation;
    @SerializedName("DispensaryLat") String DispensaryLat;
    @SerializedName("DispensaryLong") String DispensaryLong;
    @SerializedName("Tokens") List<TokenModelResult> Tokens;
    @SerializedName("Slots") List<SlotModelResult> Slots;



    public String getDispensaryID() {
        return DispensaryID;
    }

    public void setDispensaryID(String dispensaryID) {
        DispensaryID = dispensaryID;
    }

    public String getDispensaryName() {
        return DispensaryName;
    }

    public void setDispensaryName(String dispensaryName) {
        DispensaryName = dispensaryName;
    }

    public String getDispensaryLocation() {
        return DispensaryLocation;
    }

    public void setDispensaryLocation(String dispensaryLocation) {
        DispensaryLocation = dispensaryLocation;
    }

    public String getDispensaryLat() {
        return DispensaryLat;
    }

    public void setDispensaryLat(String dispensaryLat) {
        DispensaryLat = dispensaryLat;
    }

    public String getDispensaryLong() {
        return DispensaryLong;
    }

    public void setDispensaryLong(String dispensaryLong) {
        DispensaryLong = dispensaryLong;
    }

    public List<TokenModelResult> getTokens() {
        return Tokens;
    }

    public void setTokens(List<TokenModelResult> tokens) {
        Tokens = tokens;
    }

    public List<SlotModelResult> getSlots() {
        return Slots;
    }

    public void setSlots(List<SlotModelResult> slots) {
        Slots = slots;
    }
}
