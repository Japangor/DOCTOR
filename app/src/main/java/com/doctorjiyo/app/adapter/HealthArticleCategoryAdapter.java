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
import com.doctorjiyo.app.API.Model.HealthArticleCategoryModel;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.HealthArticle.HealthArticleListActivity;

public class HealthArticleCategoryAdapter extends RecyclerView.Adapter<HealthArticleCategoryAdapter.ViewHolder> {


    private ArrayList<HealthArticleCategoryModel> healthArticleModels;
    private AppCompatActivity activity;
    private String username;
    Context mContext;

    public HealthArticleCategoryAdapter(ArrayList<HealthArticleCategoryModel> healthArticleModels, Context mContext) {
        this.healthArticleModels = healthArticleModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_health_article_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        HealthArticleCategoryModel healthArticleCategoryModel=healthArticleModels.get(position);

        holder.HealthArticleCategoriesName.setText(healthArticleCategoryModel.getHealthArticleCategoriesName());
        holder.Description.setText(healthArticleCategoryModel.getDescription());
        Glide.with(mContext).load(healthArticleCategoryModel.getImagePath()).placeholder(R.drawable.img_applogo).into(holder.ImagePath);
        holder.card_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,HealthArticleListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("HealthArticleCategoriesID",healthArticleModels.get(position).getHealthArticleCategoriesID());
                intent.putExtra("HealthArticleCategoriesName",healthArticleModels.get(position).getHealthArticleCategoriesName());
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return healthArticleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView HealthArticleCategoriesName,Description;
        ImageView ImagePath;
        LinearLayout card_article;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        card_article=itemView.findViewById(R.id.card_article);
        HealthArticleCategoriesName=itemView.findViewById(R.id.HealthArticleCategoriesName);
        Description=itemView.findViewById(R.id.Description);
        ImagePath=itemView.findViewById(R.id.ImagePath);

    }
}
}
