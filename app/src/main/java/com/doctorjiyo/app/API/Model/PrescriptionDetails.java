package com.doctorjiyo.app.API.Model;

import com.google.gson.annotations.SerializedName;

public class PrescriptionDetails {
    @SerializedName("PrescriptionDetailID")
    String PrescriptionDetailID;
    @SerializedName("PrescriptionID")
    String PrescriptionID;
    @SerializedName("DrugName")
    String DrugName;
    @SerializedName("Dosage")
    String Dosage;
    @SerializedName("Qty")
    String Qty;
    @SerializedName("FormulationType")
    String FormulationType;
    @SerializedName("Instruction")
    String Instruction;
    @SerializedName("NoOfDays")
    String NoOfDays;

    public String getPrescriptionDetailID() {
        return PrescriptionDetailID;
    }

    public void setPrescriptionDetailID(String prescriptionDetailID) {
        PrescriptionDetailID = prescriptionDetailID;
    }

    public String getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String drugName) {
        DrugName = drugName;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getFormulationType() {
        return FormulationType;
    }

    public void setFormulationType(String formulationType) {
        FormulationType = formulationType;
    }

    public String getInstruction() {
        return Instruction;
    }

    public void setInstruction(String instruction) {
        Instruction = instruction;
    }

    public String getNoOfDays() {
        return NoOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        NoOfDays = noOfDays;
    }
}
