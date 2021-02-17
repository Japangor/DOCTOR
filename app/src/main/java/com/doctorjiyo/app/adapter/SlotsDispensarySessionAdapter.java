package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doctorjiyo.app.API.Model.SlotModelResult;
import com.doctorjiyo.app.API.Model.SlotTimesModel;
import com.doctorjiyo.app.API.Model.TokenModelResult;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmbetComfirmActivity;

import java.util.List;

public class SlotsDispensarySessionAdapter extends RecyclerView.Adapter<SlotsDispensarySessionAdapter.ViewHolder> {

    Context mContext;
    List<SlotModelResult> slotModelResults;
    String BookingDate;
    RecyclerView.LayoutManager layoutManager;

    public SlotsDispensarySessionAdapter(Context mContext, List<SlotModelResult> slotModelResults, String BookingDate) {
        this.mContext = mContext;
        this.slotModelResults = slotModelResults;
        this.BookingDate=BookingDate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_dispensory_slots_session, parent, false);
        return new SlotsDispensarySessionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SlotModelResult slotModelResult=slotModelResults.get(position);

        final String DispensarySession=slotModelResult.getDispensaryTiming();

        holder.DispensarySession.setText(DispensarySession);
        holder.DispensaryTiming.setText("("+slotModelResult.getTimeFrom()+"-"+slotModelResult.getTimeTo()+")");

        loadSlots(slotModelResult.getSlotTimes(),slotModelResult.getDispensaryID(),slotModelResult.getDispensaryTiming(),holder);

    }

    void loadSlots(List<SlotTimesModel> slotTimesModels,String DispensaryID,String DispensaryTiming, ViewHolder holder){

        layoutManager  = new GridLayoutManager(mContext,3);;
        holder.recyclerView.setLayoutManager(layoutManager);

        SlotTimesAdapter recyclerViewAdapter = new SlotTimesAdapter(mContext, slotTimesModels,BookingDate,DispensaryID,DispensaryTiming);

        holder.recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return slotModelResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView DispensarySession,DispensaryTiming;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DispensarySession=itemView.findViewById(R.id.DispensarySession);
            DispensaryTiming=itemView.findViewById(R.id.DispensaryTiming);
            recyclerView=itemView.findViewById(R.id.recycler_view);

        }
    }
}
