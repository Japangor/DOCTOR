package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class HealthArticleCategoryModel {

    @SerializedName("HealthArticleCategoriesID")
    private String HealthArticleCategoriesID;
    @SerializedName("HealthArticleCategoriesName")
    private String HealthArticleCategoriesName;
    @SerializedName("Description")
    private String Description;
    @SerializedName("ImagePath")
    private String ImagePath;

    public HealthArticleCategoryModel(String healthArticleCategoriesID, String healthArticleCategoriesName, String description, String imagePath) {
        HealthArticleCategoriesID = healthArticleCategoriesID;
        HealthArticleCategoriesName = healthArticleCategoriesName;
        Description = description;
        ImagePath = imagePath;
    }

    public String getHealthArticleCategoriesID() {
        return HealthArticleCategoriesID;
    }

    public void setHealthArticleCategoriesID(String healthArticleCategoriesID) {
        HealthArticleCategoriesID = healthArticleCategoriesID;
    }

    public String getHealthArticleCategoriesName() {
        return HealthArticleCategoriesName;
    }

    public void setHealthArticleCategoriesName(String healthArticleCategoriesName) {
        HealthArticleCategoriesName = healthArticleCategoriesName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
