package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class MedicalRecordModel {
    @SerializedName("RecordID")
    private String RecordID;
    @SerializedName("PatientID")
    private String PatientID;
    @SerializedName("PrescriptionID")
    private String PrescriptionID;
    @SerializedName("RecordName")
    private String RecordName;
    @SerializedName("RecordCatName")
    private String RecordCatID;
    @SerializedName("Notes")
    private String Notes;
    @SerializedName("CreatedAt")
    private String CreatedAt;
    @SerializedName("FilePath")
    private String FilePath;

    public boolean expanded = false;
    public boolean parent = false;

    public MedicalRecordModel(String recordID, String patientID, String recordName, String recordCatID, String notes, String createdAt, String filePath) {
        RecordID = recordID;
        PatientID = patientID;
        RecordName = recordName;
        RecordCatID = recordCatID;
        Notes = notes;
        CreatedAt = createdAt;
        FilePath = filePath;
    }

    public String getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getRecordName() {
        return RecordName;
    }

    public void setRecordName(String recordName) {
        RecordName = recordName;
    }

    public String getRecordCatID() {
        return RecordCatID;
    }

    public void setRecordCatID(String recordCatID) {
        RecordCatID = recordCatID;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
}
