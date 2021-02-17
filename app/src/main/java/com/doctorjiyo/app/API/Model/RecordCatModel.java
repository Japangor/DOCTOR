package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class RecordCatModel {

    @SerializedName("RecordID")
    private String RecordID;

    @SerializedName("RecordCatName")
    private String RecordCatName;

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getRecordCatName() {
        return RecordCatName;
    }

    public void setRecordCatName(String recordCatName) {
        RecordCatName = recordCatName;
    }
}
