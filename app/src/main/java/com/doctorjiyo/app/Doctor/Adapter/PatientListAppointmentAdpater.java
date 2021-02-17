package com.doctorjiyo.app.Doctor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.doctorjiyo.app.Doctor.API.Model.AppointmentList;
import com.doctorjiyo.app.Doctor.Activity.Doctor_Appointment_Activity;
import com.doctorjiyo.app.R;

public class PatientListAppointmentAdpater extends RecyclerView.Adapter<PatientListAppointmentAdpater.ViewHolder> {

    private Context mContext;
    private List<AppointmentList> appointmentLists;

    public PatientListAppointmentAdpater(Context mContext, ArrayList<AppointmentList> appointmentLists) {
        this.mContext = mContext;
        this.appointmentLists = appointmentLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.doctor_card_item_appointment, parent, false);
        return new PatientListAppointmentAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AppointmentList mappointmentList=appointmentLists.get(position);

        holder.patientname.setText(mappointmentList.getPatientname());
        holder.date.setText(mappointmentList.getDate());
        holder.status.setText(mappointmentList.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, Doctor_Appointment_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("appointmentid",mappointmentList.getAppointmentid());
                intent.putExtra("patientname",mappointmentList.getPatientname());
                intent.putExtra("patientcontact",mappointmentList.getPatientcontact());
                intent.putExtra("patientemail",mappointmentList.getPatientemail());
                intent.putExtra("date",mappointmentList.getDate());
                intent.putExtra("doctorname",mappointmentList.getDoctorname());
                intent.putExtra("purpose",mappointmentList.getPurpose());
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return appointmentLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientname,date,status;
        CardView card;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            patientname=itemView.findViewById(R.id.patientname);
            date=itemView.findViewById(R.id.date);
            status=itemView.findViewById(R.id.status);
            card=itemView.findViewById(R.id.card);


        }
    }
}
