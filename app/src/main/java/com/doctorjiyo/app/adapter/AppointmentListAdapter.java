package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.AppointmentListModel;
import com.doctorjiyo.app.API.Model.AppointmentSlotModel;
import com.doctorjiyo.app.API.Model.AppointmentTokenModel;
import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.Model.SlotTimesModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.HealthArticle.SingleHealthArticleActivity;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.ViewHolder> {


    private ArrayList<AppointmentListModel> appointmentListModels;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;

    public AppointmentListAdapter(ArrayList<AppointmentListModel> appointmentListModels, Context mContext) {
        this.appointmentListModels = appointmentListModels;
        this.mContext= mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_appointmentist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final AppointmentListModel appointmentListModel=appointmentListModels.get(position);

        loadTokenAppointmentList(appointmentListModel.getTokens(),holder);
        loadSlotAppointmentList(appointmentListModel.getSlots(),holder);

    }

    void loadTokenAppointmentList(List<AppointmentTokenModel> appointmentTokenModels, ViewHolder holder){

        layoutManager = new LinearLayoutManager(mContext);
        holder.recycler_view_token.setLayoutManager(layoutManager);

        AppointmentTokenListAdapter recyclerViewAdapter = new AppointmentTokenListAdapter(appointmentTokenModels,mContext);

        holder.recycler_view_token.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    void loadSlotAppointmentList(List<AppointmentSlotModel> appointmentSlotModels, ViewHolder holder){

        layoutManager =  new LinearLayoutManager(mContext);
        holder.recycler_view_slot.setLayoutManager(layoutManager);

        AppointmentSlotListAdapter recyclerViewAdapter = new AppointmentSlotListAdapter(appointmentSlotModels,mContext);

        holder.recycler_view_slot.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return appointmentListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recycler_view_slot,recycler_view_token;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        recycler_view_slot=itemView.findViewById(R.id.recycler_view_slot);
        recycler_view_token=itemView.findViewById(R.id.recycler_view_token);


    }
}
}
