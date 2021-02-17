package com.doctorjiyo.app.activity.Medical_Record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.Model.PrescriptionDetails;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.MedicalRecordsAdapter;
import com.doctorjiyo.app.adapter.SingleMedicineAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptionRecordListActivity extends AppCompatActivity {

    TextView title;
    String PrescriptionID,PrescriptionTitle,PatientID;
    ImageButton close;
    FloatingActionButton uploadRecord;

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescription_record_list);

        Intent intent=getIntent();
        PatientID=intent.getStringExtra("userid");
        PrescriptionID=intent.getStringExtra("PrescriptionID");
        PrescriptionTitle=intent.getStringExtra("PrescriptionTitle");

        initComponents();

        title.setText(PrescriptionTitle);

        if (PatientID.equals("0")){
            uploadRecord.setVisibility(View.GONE);
            loadPrescriptionDetails("0");
        }else {
            uploadRecord.setVisibility(View.VISIBLE);
            uploadRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(getApplicationContext(),Upload_Medical_Records_Activity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("userid",PatientID);
                    intent1.putExtra("PrescriptionID",PrescriptionID);
                    startActivity(intent1);
                }
            });
            loadPrescriptionDetails(PatientID);
        }




        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initComponents() {

        recyclerView=findViewById(R.id.recycler_view);
        close=findViewById(R.id.close);
        title=findViewById(R.id.title);
        uploadRecord=findViewById(R.id.upload_record);


    }

    private void loadPrescriptionDetails(final String patientID) {

        APIService service = RetrofitURL1.getClient().create(APIService.class);
        Call<ArrayList<MedicalRecordModel>> call = service.getPrescriptionRecordList(
                PrescriptionID
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
                        MedicalRecordsAdapter recyclerViewAdapter= new MedicalRecordsAdapter(docList, PrescriptionRecordListActivity.this,patientID);
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
    }
}