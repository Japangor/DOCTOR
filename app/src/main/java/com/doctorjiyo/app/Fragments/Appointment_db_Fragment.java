package com.doctorjiyo.app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.doctorjiyo.app.API.Model.AppointmentListModel;
import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.adapter.AppointmentListAdapter;
import com.doctorjiyo.app.adapter.MedicalRecordsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmentActivity;

import java.util.ArrayList;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointment_db_Fragment extends Fragment {

    FloatingActionButton addAppointment;

    RecyclerView recyclerview;
    private LinearLayoutManager layoutManager;
    LinearLayout noAppointment;

    String patientID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_appointment_db, container, false);

        patientID=this.getArguments().getString("userid");

        recyclerview=view.findViewById(R.id.recyclerview);
        noAppointment=view.findViewById(R.id.noappointment);

        loadAppointment();

        addAppointment=view.findViewById(R.id.addAppointment);
        addAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BookAppointmentActivity.class));
            }
        });

        return view;
    }

    void loadAppointment(){
        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<AppointmentListModel>> call = service.getAppointmentList(
                    patientID
            );

            call.enqueue(new Callback<ArrayList<AppointmentListModel>>() {
                @Override
                public void onResponse(Call<ArrayList<AppointmentListModel>> call, Response<ArrayList<AppointmentListModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            noAppointment.setVisibility(View.GONE);
                            ArrayList<AppointmentListModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(layoutManager);
                            AppointmentListAdapter recyclerViewAdapter = new AppointmentListAdapter(docList, getContext());
                            recyclerview.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<AppointmentListModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                    noAppointment.setVisibility(View.VISIBLE);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            noAppointment.setVisibility(View.VISIBLE);
        }
    }

}
