package com.doctorjiyo.app.Doctor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import com.doctorjiyo.app.API.Model.Doc_PatientListModel;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import com.doctorjiyo.app.Doctor.Activity.Doctor_Conversation_Activity;
import com.doctorjiyo.app.Doctor.Activity.Doctor_PatientProfile_Activity;
import com.doctorjiyo.app.R;
import com.squareup.picasso.Picasso;

public class PatientChatListAdpater extends RecyclerView.Adapter<PatientChatListAdpater.ViewHolder> {

    private Context mContext;
    private List<Doc_PatientListModel> mPatientList;
    String AttendentId;

    public PatientChatListAdpater(Context mContext, List<Doc_PatientListModel> mPatientList,String AttendentId) {
        this.mContext = mContext;
        this.mPatientList = mPatientList;
        this.AttendentId=AttendentId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_doc_patientchatlist, parent, false);
        return new PatientChatListAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Doc_PatientListModel patientList=mPatientList.get(position);
        holder.name.setText(patientList.getPatientName());
        if (patientList.getImgPath().equals("")){
            Picasso.with(mContext).load(R.drawable.img_applogo).into(holder.profilepic);
        }else {
            Picasso.with(mContext).load(patientList.getImgPath()).placeholder(R.drawable.img_applogo).into(holder.profilepic);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, Doctor_PatientProfile_Activity.class);
                intent.putExtra("AttendentId",AttendentId);
                intent.putExtra("patientID",patientList.getPatientID());
                intent.putExtra("ImagePath",patientList.getImgPath());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPatientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CircleImageView profilepic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            profilepic=itemView.findViewById(R.id.profilepic);

        }
    }
}
