package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doctorjiyo.app.API.Model.AppointmentListModel;
import com.doctorjiyo.app.API.Model.AppointmentTokenModel;
import com.doctorjiyo.app.MapsActivity;
import com.doctorjiyo.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AppointmentTokenListAdapter extends RecyclerView.Adapter<AppointmentTokenListAdapter.ViewHolder> {


    private List<AppointmentTokenModel> appointmentListModels;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;

    public AppointmentTokenListAdapter(List<AppointmentTokenModel> appointmentListModels, Context mContext) {
        this.appointmentListModels = appointmentListModels;
        this.mContext= mContext;
    }

    @NonNull
    @Override
    public AppointmentTokenListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_appointmentist_token, parent, false);
        return new AppointmentTokenListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppointmentTokenListAdapter.ViewHolder holder, final int position) {

        final AppointmentTokenModel appointmentListModel=appointmentListModels.get(position);

        holder.BookingDate.setText(appointmentListModel.getBookingDate());
        holder.DispensaryName.setText(appointmentListModel.getDispensaryName());
        holder.TokenNo.setText(appointmentListModel.getTokenNo());
        holder.EstimateTime.setText(appointmentListModel.getEstimateTime());
        holder.PurposeOfVisit.setText(appointmentListModel.getPurposeOfVisit());

        holder.getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(mContext, MapsActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra("dispensaryname", appointmentListModel.getDispensaryName());
                intent2.putExtra("Lat", appointmentListModel.getDispensaryLat());
                intent2.putExtra("Long",appointmentListModel.getDispensaryLong());
                intent2.putExtra("Location",appointmentListModel.getDispensaryLocation());
                mContext.startActivity(intent2);
            }
        });

    }




    @Override
    public int getItemCount() {
        return appointmentListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView BookingDate,DispensaryName,TokenNo,EstimateTime,PurposeOfVisit;
        FloatingActionButton getLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            getLocation=itemView.findViewById(R.id.getLocation);
            BookingDate=itemView.findViewById(R.id.BookingDate);
            DispensaryName=itemView.findViewById(R.id.DispensaryName);
            TokenNo=itemView.findViewById(R.id.TokenNo);
            EstimateTime=itemView.findViewById(R.id.EstimateTime);
            PurposeOfVisit=itemView.findViewById(R.id.PurposeOfVisit);


        }
    }
}
