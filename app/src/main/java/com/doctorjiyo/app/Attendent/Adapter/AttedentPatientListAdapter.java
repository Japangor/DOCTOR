package com.doctorjiyo.app.Attendent.Adapter;

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
import com.doctorjiyo.app.Attendent.Actiivty.NewMakeAppointemtActivity;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import com.doctorjiyo.app.R;

public class AttedentPatientListAdapter extends RecyclerView.Adapter<AttedentPatientListAdapter.ViewHolder> {

private Context mContext;
private List<PatientList> mPatientList;

public AttedentPatientListAdapter(Context mContext, List<PatientList> mPatientList) {
        this.mContext = mContext;
        this.mPatientList = mPatientList;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_doc_patientchatlist, parent, false);
        return new AttedentPatientListAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

final PatientList patientList=mPatientList.get(position);
        holder.name.setText(patientList.getPatientName());
        if (patientList.getPatientPhoto()==null){
        holder.profilepic.setImageResource(R.drawable.photo_female_6);
        }else{
        Glide.with(mContext).load(patientList.getPatientPhoto()).into(holder.profilepic);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent=new Intent(mContext, NewMakeAppointemtActivity.class);
        intent.putExtra("patientID",patientList.getPatinetID());
        intent.putExtra("doctorid","2");
        intent.putExtra("patientname",patientList.getPatientName());
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
