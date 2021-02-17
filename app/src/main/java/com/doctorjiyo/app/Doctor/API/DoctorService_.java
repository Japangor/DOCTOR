package com.doctorjiyo.app.Doctor.API;

import java.util.ArrayList;

import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DoctorService_ {

    @GET("PatientsListAPI")
    Call<ArrayList<PatientList>> getPatientList();

}
