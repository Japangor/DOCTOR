package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Medical_Record.Upload_Medical_Records_Activity;
import com.doctorjiyo.app.activity.WebView.WebViewActivity;
import com.doctorjiyo.app.utils.Tools;
import com.doctorjiyo.app.utils.ViewAnimation;

public class MedicalRecordsAdapter extends RecyclerView.Adapter<MedicalRecordsAdapter.ViewHolder> {


    private ArrayList<MedicalRecordModel> medicalRecordModels;
    Context mcContext;
    String PatientID;

    public MedicalRecordsAdapter(ArrayList<MedicalRecordModel> medicalRecordModels, Context mcContext, String patientID) {
        this.medicalRecordModels = medicalRecordModels;
        this.mcContext = mcContext;
        PatientID = patientID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_medical_record, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

    final MedicalRecordModel r=medicalRecordModels.get(position);



        holder.record_name.setText(medicalRecordModels.get(position).getRecordName());
        holder.record_type.setText(medicalRecordModels.get(position).getRecordCatID());
        holder.notes.setText(medicalRecordModels.get(position).getNotes());
        holder.createdat.setText(medicalRecordModels.get(position).getCreatedAt());

       // Glide.with(mcContext).load(medicalRecordModels.get(position).getRecord()).into(holder.doc_pic);

        holder.viewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcContext, WebViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("filepath",medicalRecordModels.get(position).getFilePath());
                intent.putExtra("RecordID",medicalRecordModels.get(position).getRecordID());
                mcContext.startActivity(intent);
            }
        });

        Toast.makeText(mcContext,PatientID,Toast.LENGTH_LONG).show();

        if (PatientID=="0"){
            holder.editRecord.setVisibility(View.GONE);
        }else {
            holder.editRecord.setVisibility(View.VISIBLE);
            holder.editRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mcContext, Upload_Medical_Records_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("userid",medicalRecordModels.get(position).getPatientID());
                    intent.putExtra("PrescriptionID",medicalRecordModels.get(position).getPrescriptionID());
                    intent.putExtra("RecordID",medicalRecordModels.get(position).getRecordID());
                    intent.putExtra("RecordName",medicalRecordModels.get(position).getRecordName());
                    intent.putExtra("Notes",medicalRecordModels.get(position).getNotes());
                    intent.putExtra("RecordCatName",medicalRecordModels.get(position).getRecordCatID());
                    mcContext.startActivity(intent);
                }
            });
        }



        holder.bt_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayoutExpand(!r.expanded, v, holder.lyt_expand);
                medicalRecordModels.get(position).expanded = show;
            }
        });


        // void recycling view
        if(r.expanded){
            holder.lyt_expand.setVisibility(View.VISIBLE);
        } else {
            holder.lyt_expand.setVisibility(View.GONE);
        }
        Tools.toggleArrow(r.expanded, holder.bt_expand, false);






    }

    @Override
    public int getItemCount() {
        return medicalRecordModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView doc_pic;
        TextView record_name,record_type,notes,createdat;
        Button viewRecord,editRecord;
        ImageButton bt_expand;
        public View lyt_expand;
        public View lyt_parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doc_pic=itemView.findViewById(R.id.doc_img);
            record_name=itemView.findViewById(R.id.record_name);
            record_type=itemView.findViewById(R.id.record_type);
            viewRecord=itemView.findViewById(R.id.ViewRecord);
            bt_expand = itemView.findViewById(R.id.bt_expand);
            editRecord=itemView.findViewById(R.id.EditRecord);
            lyt_expand = itemView.findViewById(R.id.lyt_expand);
            lyt_parent = itemView.findViewById(R.id.lyt_parent);
            notes=itemView.findViewById(R.id.notes);
            createdat=itemView.findViewById(R.id.createat);

        }
    }
    private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        Tools.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }
}
