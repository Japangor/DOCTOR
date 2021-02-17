package com.doctorjiyo.app.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.doctorjiyo.app.API.Model.Chat;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.MessegeAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class Chats_db_Fragment extends Fragment {


    RecyclerView recyclerView;
    List<Chat> chatList;
    MessegeAdapter recyclerViewAdapter;
    Intent intent;
    String userid;
    String patientID;

    RelativeLayout chatlay;
    EditText insertmsg;
    FloatingActionButton sendmsg;

    private LinearLayoutManager layoutManager;

    private final static String FRAGMENT_TAG = "FRAGMENTB_TAG";

    DatabaseReference reference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chats_db, container, false);

        chatlay=view.findViewById(R.id.chatlay);


        patientID=this.getArguments().getString("userid");
        Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();





        recyclerView=view.findViewById(R.id.recycler_view_msg);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        chatList=new ArrayList<>();
        recyclerViewAdapter=new MessegeAdapter(getContext(),chatList);
        recyclerView.setAdapter(recyclerViewAdapter);



        insertmsg=view.findViewById(R.id.insert_msg);
        sendmsg=view.findViewById(R.id.btn_send);
        chatList=new ArrayList<>();
        //intent=getActivity().getIntent();
        //userid= FirebaseInstanceId.getInstance().getToken();
        //Log.e("UID",userid);


        //final String sender=userid;
        final String receiver="1";
        final String sendertype="Patient";

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String msg=insertmsg.getText().toString();
                    if (TextUtils.isEmpty(msg)){
                        Snackbar.make(v,"Type message...",Snackbar.LENGTH_LONG).show();
                    }else if (!TextUtils.isEmpty(msg)){
                        sendMessage(patientID, receiver, msg, sendertype);
                        insertmsg.setText(null);
                    }

            }
        });


         readMessage();

        return view;
    }

    private void sendMessage(String sender, final String receiver, String message,String sendertype)
    {



        SimpleDateFormat stf = new SimpleDateFormat("h:mm a");
        String currentTime = stf.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = sdf.format(new Date());

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("patientid",sender);
        hashMap.put("doctorid",receiver);
        hashMap.put("message",message);
        hashMap.put("sendertype",sendertype);
        hashMap.put("msgTime",currentTime);
        hashMap.put("msgData",currentDate);

        reference.child("chats").push().setValue(hashMap);

        //add user to chat Fragment
        final DatabaseReference chatRef=FirebaseDatabase.getInstance().getReference().child(sender).child(sender);
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists())
                {
                    chatRef.child("id").setValue(patientID);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void readMessage()
    {

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("chats");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                chatList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    Chat chat= dataSnapshot.getValue(Chat.class);
                   if (chat.getPatientid().equals(patientID)){
                       chatList.add(chat);
                   }

                    //chatList.add(chat);
                }

                recyclerViewAdapter= new MessegeAdapter(getContext(), chatList);

                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(),"fail"+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}



