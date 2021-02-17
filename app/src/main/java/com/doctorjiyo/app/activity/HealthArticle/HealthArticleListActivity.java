package com.doctorjiyo.app.activity.HealthArticle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.HealthArticleAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthArticleListActivity extends AppCompatActivity {

    ShimmerRecyclerView recycler_view_article;
    private LinearLayoutManager layoutManager;

    TextView category_name;
    ImageView close;

    Intent intent;


    String HealthArticleCategoriesID,HealthArticleCategoriesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_list);

        intent=getIntent();
        HealthArticleCategoriesID=intent.getStringExtra("HealthArticleCategoriesID");
        HealthArticleCategoriesName=intent.getStringExtra("HealthArticleCategoriesName");

        category_name=findViewById(R.id.category_name);
        category_name.setText(HealthArticleCategoriesName);

        recycler_view_article=findViewById(R.id.recycler_view_article);
        loadArticle();
        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadArticle() {
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
        // progressDialog.show();

        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<HealthArticleListModel>> call = service.getArticleList(
                    HealthArticleCategoriesID
            );

            call.enqueue(new Callback<ArrayList<HealthArticleListModel>>() {
                @Override
                public void onResponse(Call<ArrayList<HealthArticleListModel>> call, Response<ArrayList<HealthArticleListModel>> response) {
                    //      progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<HealthArticleListModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recycler_view_article.setLayoutManager(layoutManager);

                            HealthArticleAdapter recyclerViewAdapter = new HealthArticleAdapter(docList, getApplicationContext());

                            recycler_view_article.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<HealthArticleListModel>> call, Throwable t) {
                    //       progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
