package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.doctorjiyo.app.API.Model.SlotModelResult;
import com.doctorjiyo.app.API.Model.SlotTimesModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmbetComfirmActivity;

public class SlotTimesAdapter extends RecyclerView.Adapter< SlotTimesAdapter.ViewHolder> {

    public static final int BOOKED=0;
    public static final int AVAILABLE=1;
    Context context;
    List<SlotTimesModel> slotTimesModels;
    String BookingDate;
    String DispensaryID;
    String DispensaryTiming;

    public SlotTimesAdapter(Context context, List<SlotTimesModel> slotTimesModels, String BookingDate,String DispensaryID,String DispensaryTiming) {
        this.context = context;
        this.slotTimesModels = slotTimesModels;
        this.BookingDate=BookingDate;
        this.DispensaryID=DispensaryID;
        this.DispensaryTiming=DispensaryTiming;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==BOOKED){
            View view= LayoutInflater.from(context).inflate(R.layout.card_item_time_slots_booked,parent,false);
            return new SlotTimesAdapter.ViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(context).inflate(R.layout.card_item_time_slots,parent,false);
            return new SlotTimesAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SlotTimesModel slotTimesModel = slotTimesModels.get(position);
        holder.time.setText(slotTimesModel.getSlotTime());

        if (!slotTimesModel.getAvailableSlot().equals("true")){
            holder.time.setClickable(false);
        }else{
            holder.time.setClickable(true);
            holder.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"clicked"+slotTimesModel.getSlotTime(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, BookAppointmbetComfirmActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("AptType","Slot");
                    intent.putExtra("DispensaryID",DispensaryID);
                    intent.putExtra("BookingDate",BookingDate);
                    intent.putExtra("DispensaryTiming",DispensaryTiming);
                    intent.putExtra("SlotTime",slotTimesModel.getSlotTime());
                    context.startActivity(intent);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return slotTimesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time=itemView.findViewById(R.id.time);
        }
    }

    @Override
    public int getItemViewType(int position) {


        if (slotTimesModels.get(position).getAvailableSlot().equals("true")){
            return AVAILABLE;
        }else {

            return BOOKED;
        }

    }


}
