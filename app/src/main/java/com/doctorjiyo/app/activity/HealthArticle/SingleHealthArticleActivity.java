package com.doctorjiyo.app.activity.HealthArticle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.Model.ResultHealthArticleResponse;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.adapter.HealthArticleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.doctorjiyo.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleHealthArticleActivity extends AppCompatActivity {

    ImageView ImagePath;
    TextView HealthArticleCategoriesName,
            HealthArticleTitle,
            ShortDesc,
            HealthArticleDate,
            PostedBy,
            Heading1,
            Text1,
            Heading2,
            Text2,
            Heading3,
            Text3,
            Heading4,
            Text4,
            Heading5,
            Text5;

    String HealthArticleID;
    FloatingActionButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_health_article);

        Intent intent = getIntent();
        HealthArticleID = intent.getStringExtra("HealthArticleID");

        close=findViewById(R.id.close);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initComponent();

        loadArticle(HealthArticleID);


    }

    void initComponent(){
        ImagePath=findViewById(R.id.ImagePath);
        HealthArticleCategoriesName=findViewById(R.id.HealthArticleCategoriesName);
        HealthArticleTitle=findViewById(R.id.HealthArticleTitle);
        ShortDesc=findViewById(R.id.ShortDesc);
        HealthArticleDate=findViewById(R.id.HealthArticleDate);
        PostedBy=findViewById(R.id.PostedBy);
        Heading1=findViewById(R.id.Heading1);
        Text1=findViewById(R.id.Text1);
                Heading2=findViewById(R.id.Heading2);
                Text2=findViewById(R.id.Text2);
                Heading3=findViewById(R.id.Heading3);
                Text3=findViewById(R.id.Text3);
                Heading4=findViewById(R.id.Heading4);
                Text4=findViewById(R.id.Text4);
                Heading5=findViewById(R.id.Heading5);
                Text5=findViewById(R.id.Text5);
    }

    private void loadArticle(String healthArticleID) {

        APIService service = RetrofitURL1.getClient().create(APIService.class);
        Call<ResultHealthArticleResponse> call = service.getArticle(
                healthArticleID
        );


        call.enqueue(new Callback<ResultHealthArticleResponse>() {
            @Override
            public void onResponse(Call<ResultHealthArticleResponse> call, Response<ResultHealthArticleResponse> response) {

                if (response.code()==200||response.code()==201){
                   if (!response.equals(null)){
                       HealthArticleCategoriesName.setText(response.body().getHealthArticleCategoriesName());
                       HealthArticleTitle.setText(response.body().getHealthArticleTitle());
                       ShortDesc.setText(response.body().getShortDesc());
                       HealthArticleDate.setText(response.body().getHealthArticleDate());
                       PostedBy.setText(response.body().getPostedBy());
                       Heading1.setText(response.body().getHeading1());
                       Text1.setText(response.body().getText1());
                       Heading2.setText(response.body().getHeading2());
                       Text2.setText(response.body().getText2());
                       Heading3.setText(response.body().getHeading3());
                       Text3.setText(response.body().getText3());
                       Heading4.setText(response.body().getHeading4());
                       Text4.setText(response.body().getText4());
                       Heading5.setText(response.body().getHeading5());
                       Text5.setText(response.body().getText5());
                   }
                }

            }

            @Override
            public void onFailure(Call<ResultHealthArticleResponse> call, Throwable t) {

                Log.e("Error2 : ",t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
}