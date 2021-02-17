package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class IntroHeadingModel {


    @SerializedName("IconSlide1")
    String IconSlide1;
    @SerializedName("IconSlide2")
    String IconSlide2;
    @SerializedName("IconSlide3")
    String IconSlide3;
    @SerializedName("HeadingSlide1")
    String HeadingSlide1;
    @SerializedName("HeadingSlide2")
    String HeadingSlide2;
    @SerializedName("HeadingSlide3")
    String HeadingSlide3;
    @SerializedName("TextSlide1")
    String TextSlide1;
    @SerializedName("TextSlide2")
    String TextSlide2;
    @SerializedName("TextSlide3")
    String TextSlide3;
    @SerializedName("BgColorSlide1")
    String BgColorSlide1;
    @SerializedName("BgColorSlide2")
    String BgColorSlide2;
    @SerializedName("BgColorSlide3")
    String BgColorSlide3;
    @SerializedName("success") private Boolean success;
    @SerializedName("message") private String message;

    public IntroHeadingModel(String iconSlide1, String iconSlide2, String iconSlide3, String headingSlide1, String headingSlide2, String headingSlide3, String textSlide1, String textSlide2, String textSlide3) {
        this.IconSlide1 = iconSlide1;
        this.IconSlide2 = iconSlide2;
        this.IconSlide3 = iconSlide3;
        this.HeadingSlide1 = headingSlide1;
        this.HeadingSlide2 = headingSlide2;
        this.HeadingSlide3 = headingSlide3;
        this.TextSlide1 = textSlide1;
        this.TextSlide2 = textSlide2;
        this.TextSlide3 = textSlide3;

    }

    public String getIconSlide1() {
        return IconSlide1;
    }

    public void setIconSlide1(String iconSlide1) {
        IconSlide1 = iconSlide1;
    }

    public String getIconSlide2() {
        return IconSlide2;
    }

    public void setIconSlide2(String iconSlide2) {
        IconSlide2 = iconSlide2;
    }

    public String getIconSlide3() {
        return IconSlide3;
    }

    public void setIconSlide3(String iconSlide3) {
        IconSlide3 = iconSlide3;
    }

    public String getHeadingSlide1() {
        return HeadingSlide1;
    }

    public void setHeadingSlide1(String headingSlide1) {
        HeadingSlide1 = headingSlide1;
    }

    public String getHeadingSlide2() {
        return HeadingSlide2;
    }

    public void setHeadingSlide2(String headingSlide2) {
        HeadingSlide2 = headingSlide2;
    }

    public String getHeadingSlide3() {
        return HeadingSlide3;
    }

    public void setHeadingSlide3(String headingSlide3) {
        HeadingSlide3 = headingSlide3;
    }

    public String getTextSlide1() {
        return TextSlide1;
    }

    public void setTextSlide1(String textSlide1) {
        TextSlide1 = textSlide1;
    }

    public String getTextSlide2() {
        return TextSlide2;
    }

    public void setTextSlide2(String textSlide2) {
        TextSlide2 = textSlide2;
    }

    public String getTextSlide3() {
        return TextSlide3;
    }

    public void setTextSlide3(String textSlide3) {
        TextSlide3 = textSlide3;
    }

    public String getBgColorSlide1() {
        return BgColorSlide1;
    }

    public void setBgColorSlide1(String bgColorSlide1) {
        BgColorSlide1 = bgColorSlide1;
    }

    public String getBgColorSlide2() {
        return BgColorSlide2;
    }

    public void setBgColorSlide2(String bgColorSlide2) {
        BgColorSlide2 = bgColorSlide2;
    }

    public String getBgColorSlide3() {
        return BgColorSlide3;
    }

    public void setBgColorSlide3(String bgColorSlide3) {
        BgColorSlide3 = bgColorSlide3;
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
}
