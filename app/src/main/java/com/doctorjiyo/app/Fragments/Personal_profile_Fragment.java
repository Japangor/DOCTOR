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
public class Personal_profile_Fragment extends Fragment {

    String patientID;
    TextView ContactNumber,emgContactNumber,Email,Gender,DOB,MaritalStatus;

    PatientProfile patientProfile;



    public Personal_profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_personal_profile, container, false);

        patientID=this.getArguments().getString("userid");
        //Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();

        initComponents(view);

        readProfileData();


        return view;
    }
    public void initComponents(View view){

        ContactNumber=view.findViewById(R.id.contactnumber);
        emgContactNumber=view.findViewById(R.id.emgContactnumber);
        Email=view.findViewById(R.id.email);
        Gender=view.findViewById(R.id.gender);
        DOB=view.findViewById(R.id.dob);
        MaritalStatus=view.findViewById(R.id.maritalstatus);

    }

    public void readProfileData(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponsePatientProfile> call=service.getPatientProfile(
                patientID
        );
        call.enqueue(new Callback<ResponsePatientProfile>() {
            @Override
            public void onResponse(Call<ResponsePatientProfile> call, Response<ResponsePatientProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){

                        ContactNumber.setText(response.body().getContactNumber().trim());
                        emgContactNumber.setText(response.body().getEmergencyContact().trim());
                        Email.setText(response.body().getEmail().trim());
                        Gender.setText(response.body().getGender().trim());
                        DOB.setText(response.body().getDOB().trim());
                        MaritalStatus.setText(response.body().getMaritalStatus().trim());
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
