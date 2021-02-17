package com.doctorjiyo.app.activity.Messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.doctorjiyo.app.API.Model.Chat;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.MessegeAdapter;

public class MessageActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    ArrayList chatList;
    MessegeAdapter recyclerViewAdapter;
    Intent intent;
    String userid;

    EditText insertmsg;
    FloatingActionButton sendmsg;

    private LinearLayoutManager layoutManager;
    private List<Chat> mChat=new ArrayList<>();

    MessegeAdapter adapter;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final RelativeLayout relativeLayout=findViewById(R.id.layout);


        recyclerView=findViewById(R.id.recycler_view_msg);

        content();

        recyclerView.setHasFixedSize(true);
        Context context;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);




        adapter=new MessegeAdapter(mChat);
        recyclerView.setAdapter(adapter);






        insertmsg=findViewById(R.id.insert_msg);
        sendmsg=findViewById(R.id.btn_send);
        chatList=new ArrayList<>();
        intent=getIntent();
        userid=intent.getStringExtra("uid");



        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  sendmessage();
                insertmsg.setText(null);
            }
        });

    }

    public void content(){
     //   loadMsg();
        refresh(2000);
    }

    private  void refresh(int milliseconds){
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                content();
            }
        };
        handler.postDelayed(runnable,milliseconds);
    }

/*
    private void loadMsg() {
        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<Chat>> call = service.getConversationList(
                    "2",
                    "2"
            );

            call.enqueue(new Callback<ArrayList<Chat>>() {
                @Override
                public void onResponse(Call<ArrayList<Chat>> call, retrofit2.Response<ArrayList<Chat>> response) {
                    //      progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            mChat = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);

                            recyclerViewAdapter = new MessegeAdapter(getApplicationContext(), mChat);

                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Chat>> call, Throwable t) {
                    //       progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sendmessage() {

        final String msg=insertmsg.getText().toString().trim();
        String pid="2";
        String did="2";
        String sender="Patient";

        Map<String,String> params=new HashMap<String, String>();
        params.put("PatientID",pid);
        params.put("DoctorID",did);
        params.put("Messages",msg);
        params.put("Sender",sender);


        APIService service=RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call=service.SendMsg(
                params
        );
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                try {
                    if (response.code()==201){
                        if (response.body().getSucccess()){
                            Toast.makeText(getApplicationContext(),"send"+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            recyclerViewAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(),"error : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"server error please try again later!",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
*/




}
