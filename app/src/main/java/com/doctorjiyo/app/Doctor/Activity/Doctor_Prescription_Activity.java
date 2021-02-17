package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Doctor.Adapter.PatientPrescriptionListAdapter;
import com.doctorjiyo.app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_Prescription_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    TextView tv_username;
    ImageButton close;
    Intent intent;
    String patientID,doctorid,patientname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__prescription);

        recyclerView=findViewById(R.id.recycler_view);

        intent=getIntent();
        patientID=intent.getStringExtra("PatientID");
        //doctorid=intent.getStringExtra("doctorid");
        patientname=intent.getStringExtra("PatientName");


        tv_username=findViewById(R.id.username);
        tv_username.setText(patientname);;

        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadPrescriptionRecords();

    }

    private void loadPrescriptionRecords() {
        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<PrescriptionModel>> call = service.getPrecriptionsList(
                    patientID
            );

            call.enqueue(new Callback<ArrayList<PrescriptionModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PrescriptionModel>> call, Response<ArrayList<PrescriptionModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<PrescriptionModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);

                            PatientPrescriptionListAdapter recyclerViewAdapter = new PatientPrescriptionListAdapter(docList, Doctor_Prescription_Activity.this,patientID);

                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PrescriptionModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
