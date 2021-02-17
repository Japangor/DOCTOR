package com.doctorjiyo.app.activity.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.Model.NotificationModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.HealthArticleAdapter;
import com.doctorjiyo.app.adapter.NotificationListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientNotificationActivity extends AppCompatActivity {

    ImageButton close;
    RecyclerView recycler_view;
    Intent intent;
    String PatientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_notification);

        intent=getIntent();
        PatientID=intent.getStringExtra("PatientID");

        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_view=findViewById(R.id.recycler_view);

        loadNotification();

    }


    void loadNotification(){
        APIService service = RetrofitURL1.getClient().create(APIService.class);
        Call<ArrayList<NotificationModel>> call = service.getNotificationList(
                PatientID
        );

        call.enqueue(new Callback<ArrayList<NotificationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NotificationModel>> call, Response<ArrayList<NotificationModel>> response) {
                //      progressDialog.dismiss();
                if (response.code()==200) {
                    if (!response.equals(null)) {
                        ArrayList<NotificationModel> docList = response.body();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recycler_view.setLayoutManager(layoutManager);

                        NotificationListAdapter recyclerViewAdapter = new NotificationListAdapter(docList, getApplicationContext());

                        recycler_view.setAdapter(recyclerViewAdapter);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NotificationModel>> call, Throwable t) {
                //       progressDialog.dismiss();
            }
        });
    }

}