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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.Model.NotificationModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.HealthArticle.SingleHealthArticleActivity;

import java.util.ArrayList;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {


    private ArrayList<NotificationModel> notificationModels;
    private AppCompatActivity activity;
    private String username;
    Context mContext;

    public NotificationListAdapter(ArrayList<NotificationModel> notificationModels, Context mContext) {
        this.notificationModels = notificationModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final NotificationModel notificationModel=notificationModels.get(position);

        holder.NotificationTitle.setText(notificationModel.getNotificationTitle());
        holder.NotificationDesc.setText(notificationModel.getNotificationDesc());

        int indexnumber=position+1;

        String index= String.valueOf(indexnumber);
        holder.indexnum.setText(index);

    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView NotificationTitle,NotificationDesc,indexnum;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        indexnum=itemView.findViewById(R.id.indexnum);
        NotificationTitle=itemView.findViewById(R.id.NotificationTitle);
        NotificationDesc=itemView.findViewById(R.id.NotificationDesc);
    }
}
}
