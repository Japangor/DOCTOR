package com.doctorjiyo.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doctorjiyo.app.API.Model.DispensaryListResult;
import com.doctorjiyo.app.API.Model.TokenModelResult;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.MapsActivity;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmbetComfirmActivity;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmentActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenDispensarySessionAdapter extends RecyclerView.Adapter<TokenDispensarySessionAdapter.ViewHolder> {

    Context mContext;
    List<TokenModelResult> tokenModelResultList;
    String DoctorID;
    LinearLayoutManager layoutManager;
    String BookingDate;

    public TokenDispensarySessionAdapter(Context mContext, List<TokenModelResult> tokenModelResultList,String BookingDate) {
        this.mContext = mContext;
        this.tokenModelResultList = tokenModelResultList;
        this.BookingDate=BookingDate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_item_dispensory_session, parent, false);
        return new TokenDispensarySessionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final TokenModelResult tokenModelResult=tokenModelResultList.get(position);

        final String DispensaryID=tokenModelResult.getDispensaryID();
        final String Token=tokenModelResult.getTokenNo();
        final String DispensarySession=tokenModelResult.getDispensaryTiming();

        holder.DispensarySession.setText(DispensarySession);
        holder.DispensaryTiming.setText("("+tokenModelResult.getTimeFrom()+"-"+tokenModelResult.getTimeTo()+")");
        holder.tokennumber.setText(tokenModelResult.getTokenNo());
        holder.estimatedtime.setText(tokenModelResult.getEstimateTime());


        holder.bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BookAppointmbetComfirmActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("AptType","Token");
                intent.putExtra("DispensaryID",DispensaryID);
                intent.putExtra("BookingDate",BookingDate);
                intent.putExtra("DispensaryTiming",DispensarySession);
                intent.putExtra("Token",Token);
                mContext.startActivity(intent);
                //((Activity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tokenModelResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView DispensarySession,DispensaryTiming,tokennumber,estimatedtime;
        Button bookappointment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DispensarySession=itemView.findViewById(R.id.DispensarySession);
            DispensaryTiming=itemView.findViewById(R.id.DispensaryTiming);
            tokennumber=itemView.findViewById(R.id.tokennumber);
            estimatedtime=itemView.findViewById(R.id.estimatedtime);
            bookappointment=itemView.findViewById(R.id.bookappointment);

        }
    }
}
