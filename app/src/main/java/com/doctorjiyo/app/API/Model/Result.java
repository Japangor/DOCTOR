package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class Result {


    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String message;

    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
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
