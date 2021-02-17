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

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.HealthArticle.SingleHealthArticleActivity;

public class HealthArticleAdapter extends RecyclerView.Adapter<HealthArticleAdapter.ViewHolder> {


    private ArrayList<HealthArticleListModel> healthArticleListModels;
    private AppCompatActivity activity;
    private String username;
    Context mContext;

    public HealthArticleAdapter(ArrayList<HealthArticleListModel> healthArticleListModels, Context mContext) {
        this.healthArticleListModels = healthArticleListModels;
        this.mContext= mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_health_article, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final HealthArticleListModel healthArticleListModel=healthArticleListModels.get(position);

        holder.HealthArticleTitle.setText(healthArticleListModel.getHealthArticleTitle());
        holder.ShortDesc.setText(healthArticleListModel.getShortDesc());
        holder.HealthArticleDate.setText(healthArticleListModel.getHealthArticleDate());
        String postedby="- "+healthArticleListModel.getPostedBy();
        holder.PostedBy.setText(postedby);

        Glide.with(mContext).load(healthArticleListModel.getImagePath()).placeholder(R.drawable.img_applogo).into(holder.ImagePath);

        holder.card_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, SingleHealthArticleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("HealthArticleID", healthArticleListModel.getHealthArticleID());

                mContext.startActivity(intent);

            }
        });





    }

    @Override
    public int getItemCount() {
        return healthArticleListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ImagePath;
        TextView HealthArticleTitle,ShortDesc,HealthArticleDate,PostedBy;
        LinearLayout card_article;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        ImagePath=itemView.findViewById(R.id.ImagePath);
        HealthArticleTitle=itemView.findViewById(R.id.HealthArticleTitle);
        ShortDesc=itemView.findViewById(R.id.ShortDesc);
        HealthArticleDate=itemView.findViewById(R.id.HealthArticleDate);
        PostedBy=itemView.findViewById(R.id.PostedBy);
        card_article=itemView.findViewById(R.id.card_article);

    }
}
}
