package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class ResponsePatientProfile {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("PatientID") String PatientID;
    @SerializedName("PatientName") String PatientName;
    @SerializedName("ImgPath") String ImgPath;
    @SerializedName("Username") String Username;
    @SerializedName("Password") String Password;
    @SerializedName("ContactNumber") String ContactNumber;
    @SerializedName("Email") String Email;
    @SerializedName("Gender") String Gender;
    @SerializedName("DOB") String DOB;
    @SerializedName("BloodGroup") String BloodGroup;
    @SerializedName("MaritalStatus") String MaritalStatus;
    @SerializedName("Height") String Height;
    @SerializedName("Weight") String Weight;
    @SerializedName("EmergencyContact") String EmergencyContact;
    @SerializedName("Location") String Location;
    @SerializedName("SmokingHabits") String SmokingHabits;
    @SerializedName("AlcoholConsumption") String AlcoholConsumption;
    @SerializedName("ActivitiesLevel") String ActivitiesLevel;
    @SerializedName("FoodPreferences") String FoodPreferences;

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
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

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getEmergencyContact() {
        return EmergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        EmergencyContact = emergencyContact;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSmokingHabits() {
        return SmokingHabits;
    }

    public void setSmokingHabits(String smokingHabits) {
        SmokingHabits = smokingHabits;
    }

    public String getAlcoholConsumption() {
        return AlcoholConsumption;
    }

    public void setAlcoholConsumption(String alcoholConsumption) {
        AlcoholConsumption = alcoholConsumption;
    }

    public String getActivitiesLevel() {
        return ActivitiesLevel;
    }

    public void setActivitiesLevel(String activitiesLevel) {
        ActivitiesLevel = activitiesLevel;
    }

    public String getFoodPreferences() {
        return FoodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        FoodPreferences = foodPreferences;
    }
}
