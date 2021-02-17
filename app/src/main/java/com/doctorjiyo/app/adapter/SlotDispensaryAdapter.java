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

import com.doctorjiyo.app.API.Model.DispensaryListResult;
import com.doctorjiyo.app.API.Model.SlotModelResult;
import com.doctorjiyo.app.MapsActivity;
import com.doctorjiyo.app.R;

import java.util.List;

public class SlotDispensaryAdapter extends RecyclerView.Adapter<SlotDispensaryAdapter.ViewHolder> {

    Context mContext;
    List<DispensaryListResult> dispensaryListResultsList;
    String BookingDate;
    LinearLayoutManager layoutManager;

    public SlotDispensaryAdapter(Context mContext, List<DispensaryListResult> dispensaryListResultsList, String bookingDate) {
        this.mContext = mContext;
        this.dispensaryListResultsList = dispensaryListResultsList;
        this.BookingDate = bookingDate;
    }

    @NonNull
    @Override
    public SlotDispensaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_book_appointmnet, parent, false);
        return new SlotDispensaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SlotDispensaryAdapter.ViewHolder holder, int position) {

        final DispensaryListResult dispensaryListResult=dispensaryListResultsList.get(position);
        holder.dispansoryname.setText(dispensaryListResult.getDispensaryName());


        loaddispensorySession(dispensaryListResult.getSlots(),holder);

        holder.viewonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(mContext, MapsActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra("dispensaryname", dispensaryListResult.getDispensaryName());
                intent2.putExtra("Lat", dispensaryListResult.getDispensaryLat());
                intent2.putExtra("Long",dispensaryListResult.getDispensaryLong());
                intent2.putExtra("Location",dispensaryListResult.getDispensaryLocation());
                mContext.startActivity(intent2);
            }
        });




    }

    void loaddispensorySession(List<SlotModelResult> slotModelResults, final SlotDispensaryAdapter.ViewHolder holder){

        layoutManager = new LinearLayoutManager(mContext);
        holder.sessionRecyclerview.setLayoutManager(layoutManager);

        SlotsDispensarySessionAdapter recyclerViewAdapter = new SlotsDispensarySessionAdapter(mContext, slotModelResults,BookingDate);

        holder.sessionRecyclerview.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dispensaryListResultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dispansoryname,location,time,viewonmap;
        RecyclerView sessionRecyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sessionRecyclerview=itemView.findViewById(R.id.sessionRecyclerview);
            viewonmap=itemView.findViewById(R.id.viewonmap);
            time=itemView.findViewById(R.id.time);
            dispansoryname=itemView.findViewById(R.id.dispansoryname);
            location=itemView.findViewById(R.id.viewonmap);

        }
    }
}