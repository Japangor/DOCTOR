package com.doctorjiyo.app.Doctor.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import com.doctorjiyo.app.Doctor.API.DoctorService_;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import com.doctorjiyo.app.Doctor.API.RetrofiltURL_Doc;
import com.doctorjiyo.app.Doctor.Adapter.PatientRecordListAdpater;
import com.doctorjiyo.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Doc_MedicalRecords_Fragment extends Fragment {


    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doc__medical_records, container, false);


        recyclerView=view.findViewById(R.id.patientlist);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        loadPatientList();

        return view;
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
                        layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        PatientRecordListAdpater patientRecordListAdpater=new PatientRecordListAdpater(getContext(),patientLists);
                        recyclerView.setAdapter(patientRecordListAdpater);
                        patientRecordListAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PatientList>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
