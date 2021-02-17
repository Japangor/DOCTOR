package com.doctorjiyo.app.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Medical_Record.Upload_Medical_Records_Activity;
import com.doctorjiyo.app.adapter.MedicalRecordsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalReport_db_Fragment extends Fragment {

    FloatingActionButton upload_record;
    LinearLayout no_record;
    ShimmerRecyclerView medical_record_recyclerview;
    private LinearLayoutManager layoutManager;

  private static final int REQUEST_CAMERA_=1;


    String patientID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_medical_report_db, container, false);

        patientID=this.getArguments().getString("userid");
        Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();

        upload_record=view.findViewById(R.id.upload_record);
        medical_record_recyclerview=view.findViewById(R.id.medical_record_recyclerview);



        upload_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Upload_Medical_Records_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userid",patientID);
                intent.putExtra("PrescriptionID","0");
                startActivity(intent);
                //startActivity(new Intent(getActivity(), Upload_Medical_Records_Activity.class));
            }
        });


        loadMedicalRecords();

        

        return view;
    }
    private void loadMedicalRecords() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        // progressDialog.show();

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
                            layoutManager = new LinearLayoutManager(getContext());
                            medical_record_recyclerview.setLayoutManager(layoutManager);
                            MedicalRecordsAdapter recyclerViewAdapter = new MedicalRecordsAdapter(docList, (AppCompatActivity) getActivity(),patientID);
                            medical_record_recyclerview.setAdapter(recyclerViewAdapter);
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
