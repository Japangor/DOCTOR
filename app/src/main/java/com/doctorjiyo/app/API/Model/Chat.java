package com.doctorjiyo.app.API.Model;

public class Chat {


    public String id,doctorid,message,patientid,sendertype,msgTime;

    public Chat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getSendertype() {
        return sendertype;
    }

    public void setSendertype(String sendertype) {
        this.sendertype = sendertype;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public Chat(String id, String doctorid, String message, String patientid, String sendertype, String msgTime) {
        this.id = id;
        this.doctorid = doctorid;
        this.message = message;
        this.patientid = patientid;
        this.sendertype = sendertype;
        this.msgTime = msgTime;
    }

}
