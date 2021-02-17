package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("NotificationID")
    private String NotificationID;

    @SerializedName("PatientID")
    private String PatientID;

    @SerializedName("NotificationTitle")
    private String NotificationTitle;

    @SerializedName("NotificationDesc")
    private String NotificationDesc;

    public NotificationModel(String notificationID, String patientID, String notificationTitle, String notificationDesc) {
        NotificationID = notificationID;
        PatientID = patientID;
        NotificationTitle = notificationTitle;
        NotificationDesc = notificationDesc;
    }

    public String getNotificationID() {
        return NotificationID;
    }

    public void setNotificationID(String notificationID) {
        NotificationID = notificationID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

    public String getNotificationDesc() {
        return NotificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        NotificationDesc = notificationDesc;
    }
}
