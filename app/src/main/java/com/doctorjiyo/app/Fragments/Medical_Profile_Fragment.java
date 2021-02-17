package com.doctorjiyo.app.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.API.Model.PatientProfile;
import com.doctorjiyo.app.API.Model.ResponsePatientProfile;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Medical_Profile_Fragment extends Fragment {


    String patientID;
    TextView bldgrp,height,weight,smoking,alcohol,food;

    PatientProfile patientProfile;

    public Medical_Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_medical__profile, container, false);

        patientID=this.getArguments().getString("userid");
        //Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();


        initComponents(view);

        readMedicalProfile();

        return view;
    }

    public void initComponents(View view){

        bldgrp=view.findViewById(R.id.bldgrp);
        height=view.findViewById(R.id.height);
        weight=view.findViewById(R.id.weight);
        smoking=view.findViewById(R.id.smokingh);
        alcohol=view.findViewById(R.id.alcohol);
        food=view.findViewById(R.id.food);

    }

    public void readMedicalProfile(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponsePatientProfile> call=service.getPatientProfile(
                patientID
        );
        call.enqueue(new Callback<ResponsePatientProfile>() {
            @Override
            public void onResponse(Call<ResponsePatientProfile> call, Response<ResponsePatientProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){
                        bldgrp.setText(response.body().getBloodGroup().trim());
                        height.setText(response.body().getHeight().trim());
                        weight.setText(response.body().getWeight().trim());
                        smoking.setText(response.body().getSmokingHabits().trim());
                        alcohol.setText(response.body().getAlcoholConsumption().trim());
                        food.setText(response.body().getFoodPreferences().trim());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePatientProfile> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}
