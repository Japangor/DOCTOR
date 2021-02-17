package com.doctorjiyo.app.Doctor.API;


import java.util.ArrayList;

import com.doctorjiyo.app.Doctor.API.Model.AppointmentList;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DoctorService {


    @GET("5e5e257b31000005ad2c221f")
    Call<ArrayList<PatientList>> getChatPatientList();

    @GET("5e60aeff330000b95197bae1")
    Call<ArrayList<AppointmentList>> getAppointmentList();

    @GET("5e5e257b31000005ad2c221f")
    Call<ArrayList<PatientList>> getMedicalRecordsPatientList();

}
