package com.doctorjiyo.app.Attendent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.doctorjiyo.app.API.Model.DispensaryTokenModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmbetComfirmActivity;

public class AttendentDispensaryAdapter extends RecyclerView.Adapter<AttendentDispensaryAdapter.ViewHolder>  {

    Context mContext;
    List<DispensaryTokenModel> mDispensaryTokenModel;

    public AttendentDispensaryAdapter(Context mContext, List<DispensaryTokenModel> mDispensaryTokenModel) {
        this.mContext = mContext;
        this.mDispensaryTokenModel = mDispensaryTokenModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_book_appointmnet, parent, false);
        return new AttendentDispensaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DispensaryTokenModel dispensaryModel=mDispensaryTokenModel.get(position);

        holder.dispansoryname.setText(dispensaryModel.getDispansaryname());
        //holder.location.setText(dispensaryModel.getLocation());
        holder.tokennumber.setText(dispensaryModel.getTokenNumber());
        holder.estimatedtime.setText(dispensaryModel.getEstimatedTime());

        holder.bookappoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BookAppointmbetComfirmActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDispensaryTokenModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dispansoryname,location,tokennumber,estimatedtime;
        Button bookappoinment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dispansoryname=itemView.findViewById(R.id.dispansoryname);
            location=itemView.findViewById(R.id.viewonmap);
            tokennumber=itemView.findViewById(R.id.tokennumber);
            estimatedtime=itemView.findViewById(R.id.estimatedtime);
            bookappoinment=itemView.findViewById(R.id.bookappointment);


        }
    }

}
