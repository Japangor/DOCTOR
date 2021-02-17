package com.doctorjiyo.app.Attendent.Actiivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.doctorjiyo.app.Attendent.Adapter.AttedentPatientListAdapter;
import com.doctorjiyo.app.Doctor.API.DoctorService_;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import com.doctorjiyo.app.Doctor.API.RetrofiltURL_Doc;
import com.doctorjiyo.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendentMakeAppointmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    FloatingActionButton addNewPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendent_make_appointment);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        loadPatientList();

        addNewPatient=findViewById(R.id.addpatient);
        addNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Attendent_CreateNewPatient_Activity.class);
                startActivity(intent);

            }
        });

    }
    private void loadPatientList(){

        DoctorService_ service= RetrofiltURL_Doc.getClient().create(DoctorService_.class);
        Call<ArrayList<PatientList>> call=service.getPatientList();

        call.enqueue(new Callback<ArrayList<PatientList>>() {
            @Override
            public void onResponse(Call<ArrayList<PatientList>> call, Response<ArrayList<PatientList>> response) {
                if (response.code()==200 ){
                    if (!response.equals(null)){
                        ArrayList<PatientList> patientLists=response.body();
                        layoutManager=new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        AttedentPatientListAdapter attedentPatientListAdapter=new AttedentPatientListAdapter(getApplicationContext(),patientLists);
                        recyclerView.setAdapter(attedentPatientListAdapter);
                        attedentPatientListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PatientList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
