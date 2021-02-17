package com.doctorjiyo.app.Doctor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Medical_Record.PrescriptionRecordListActivity;
import com.doctorjiyo.app.activity.Prescription.PrescriptionActivity;
import com.doctorjiyo.app.activity.WebView.WebViewActivity;
import com.doctorjiyo.app.utils.Tools;
import com.doctorjiyo.app.utils.ViewAnimation;

public class Doctor_MedicalRecordsAdapter extends RecyclerView.Adapter<Doctor_MedicalRecordsAdapter.ViewHolder> {


    private ArrayList<PrescriptionModel> prescriptionModels;
    private Context context;
    String patientID="0";
    Activity activity;



    public Doctor_MedicalRecordsAdapter(ArrayList<PrescriptionModel> prescriptionModels, Context context) {
        this.prescriptionModels = prescriptionModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_medical_record, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final PrescriptionModel prescriptionModel=prescriptionModels.get(position);
        holder.PrescriptionTitle.setText(prescriptionModel.getPrescriptionTitle());
        holder.PatientName.setText(prescriptionModel.getPatientName());
        holder.DoctorName.setText(prescriptionModel.getDoctorName());
        holder.PrescriptionDate.setText(prescriptionModel.getPrescriptionDate());
        final String Diagnosis=prescriptionModel.getDiagnosis();

        holder.Diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelPop(Diagnosis);
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PrescriptionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PrescriptionID",prescriptionModel.getPrescriptionID());
                intent.putExtra("PrescriptionTitle",prescriptionModel.getPrescriptionTitle());
                context.startActivity(intent);
            }
        });

        holder.view_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PrescriptionRecordListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userid",patientID);
                intent.putExtra("PrescriptionID",prescriptionModel.getPrescriptionID());
                intent.putExtra("PrescriptionTitle",prescriptionModel.getPrescriptionTitle());
                context.startActivity(intent);
            }
        });

    }
    void ModelPop(String Diagnosis){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(Diagnosis);
        alertDialogBuilder.setTitle("Diagnosis");
        alertDialogBuilder.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //alertDialog.dismiss();
                    }
                });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();

    }
    @Override
    public int getItemCount() {
        return prescriptionModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView PatientName,DoctorName,PrescriptionDate,PrescriptionTitle;
        AppCompatButton Diagnosis,view_record;
        LinearLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            PatientName=itemView.findViewById(R.id.PatientName);
            DoctorName=itemView.findViewById(R.id.DoctorName);
            PrescriptionDate=itemView.findViewById(R.id.PrescriptionDate);
            PrescriptionTitle=itemView.findViewById(R.id.PrescriptionTitle);
            Diagnosis=itemView.findViewById(R.id.Diagnosis);
            view_record=itemView.findViewById(R.id.view_record);
            card=itemView.findViewById(R.id.card);


        }
    }
}
