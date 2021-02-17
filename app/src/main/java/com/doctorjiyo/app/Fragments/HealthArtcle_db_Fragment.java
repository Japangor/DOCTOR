package com.doctorjiyo.app.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.HealthArticleCategoryModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.HealthArticleCategoryAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthArtcle_db_Fragment extends Fragment {


    ShimmerRecyclerView recycler_view_article;
    private LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_health_artcle_db, container, false);

        recycler_view_article=view.findViewById(R.id.recycler_view_article);
        loadCategories();
        return view;
    }

    private void loadCategories() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
       // progressDialog.show();

        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<HealthArticleCategoryModel>> call = service.getArticleCategory();

            call.enqueue(new Callback<ArrayList<HealthArticleCategoryModel>>() {
                @Override
                public void onResponse(Call<ArrayList<HealthArticleCategoryModel>> call, Response<ArrayList<HealthArticleCategoryModel>> response) {
              //      progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<HealthArticleCategoryModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getContext());
                            recycler_view_article.setLayoutManager(layoutManager);

                            HealthArticleCategoryAdapter recyclerViewAdapter = new HealthArticleCategoryAdapter(docList, getContext());

                            recycler_view_article.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<HealthArticleCategoryModel>> call, Throwable t) {
             //       progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
