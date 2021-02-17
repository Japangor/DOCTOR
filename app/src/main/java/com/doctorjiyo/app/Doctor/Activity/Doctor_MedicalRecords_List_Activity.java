package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Doctor.Adapter.Doctor_MedicalRecordsAdapter;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.MedicalRecordsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_MedicalRecords_List_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    String patientID,patientname;
    Intent intent;
    TextView tv_username;
    ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__medical_records_list);

        recyclerView=findViewById(R.id.recycler_view);

        intent=getIntent();
        patientID=intent.getStringExtra("PatientID");
        patientname=intent.getStringExtra("PatientName");
        Toast.makeText(getApplicationContext(),patientID, Toast.LENGTH_LONG).show();



        tv_username=findViewById(R.id.username);
        tv_username.setText(patientname);;

        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadMedicalRecords();

    }

    private void loadMedicalRecords() {

        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<MedicalRecordModel>> call = service.getRecordList(
                    patientID
            );

            call.enqueue(new Callback<ArrayList<MedicalRecordModel>>() {
                @Override
                public void onResponse(Call<ArrayList<MedicalRecordModel>> call, Response<ArrayList<MedicalRecordModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<MedicalRecordModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            MedicalRecordsAdapter recyclerViewAdapter = new MedicalRecordsAdapter(docList, Doctor_MedicalRecords_List_Activity.this,"0");
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<MedicalRecordModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
