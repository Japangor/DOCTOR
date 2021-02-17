package com.doctorjiyo.app;

import com.google.gson.annotations.SerializedName;

public class ScreenItem {

    @SerializedName("Title")
    String Title;
    @SerializedName("Description")
    String Description;
    @SerializedName("ScreenImg")
    String ScreenImg;
    @SerializedName("Bgcolor")
    String Bgcolor;

    public ScreenItem(String title, String description, String screenImg, String bgcolor) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
        Bgcolor = bgcolor;
    }

    public String getBgcolor() {
        return Bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        Bgcolor = bgcolor;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(String screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getScreenImg() {
        return ScreenImg;
    }
}
