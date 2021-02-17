package com.doctorjiyo.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.SplashScreenActivity;
import com.doctorjiyo.app.activity.Medical_Record.PrescriptionRecordListActivity;
import com.doctorjiyo.app.activity.Prescription.PrescriptionActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.ViewHolder> {


    private ArrayList<PrescriptionModel> prescriptionModels;
    private Context context;
    String patientID;

    public PrescriptionListAdapter(ArrayList<PrescriptionModel> prescriptionModels, Context context,String patientID) {
        this.prescriptionModels= prescriptionModels;
        this.context = context;
        this.patientID=patientID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_prescription, parent, false);
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
                Intent intent=new Intent(context,PrescriptionActivity.class);
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
