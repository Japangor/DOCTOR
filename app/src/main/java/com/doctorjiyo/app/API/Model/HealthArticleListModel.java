package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class HealthArticleListModel {
    @SerializedName("HealthArticleID")
    private String HealthArticleID;
    @SerializedName("HealthArticleTitle")
    private String HealthArticleTitle;
    @SerializedName("ShortDesc")
    private String ShortDesc;
    @SerializedName("HealthArticleDate")
    private String HealthArticleDate;
    @SerializedName("PostedBy")
    private String PostedBy;
    @SerializedName("ImagePath")
    private String ImagePath;

    public HealthArticleListModel(String healthArticleID, String healthArticleTitle, String shortDesc, String healthArticleDate, String postedBy, String imagePath) {
        HealthArticleID = healthArticleID;
        HealthArticleTitle = healthArticleTitle;
        ShortDesc = shortDesc;
        HealthArticleDate = healthArticleDate;
        PostedBy = postedBy;
        ImagePath = imagePath;
    }

    public String getHealthArticleID() {
        return HealthArticleID;
    }

    public void setHealthArticleID(String healthArticleID) {
        HealthArticleID = healthArticleID;
    }

    public String getHealthArticleTitle() {
        return HealthArticleTitle;
    }

    public void setHealthArticleTitle(String healthArticleTitle) {
        HealthArticleTitle = healthArticleTitle;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getHealthArticleDate() {
        return HealthArticleDate;
    }

    public void setHealthArticleDate(String healthArticleDate) {
        HealthArticleDate = healthArticleDate;
    }

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
