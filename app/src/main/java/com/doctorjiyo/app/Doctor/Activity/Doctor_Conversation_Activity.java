package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.API.RetrofitURL1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.doctorjiyo.app.API.Model.Chat;
import com.doctorjiyo.app.Doctor.Adapter.ConversationAdapter;
import com.doctorjiyo.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.pchmn.materialchips.R2.id.content;

public class Doctor_Conversation_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Chat> chatList;
    ConversationAdapter recyclerViewAdapter;
    Intent intent;
    String userid;
    String patientID,doctorid,patientname;

    RelativeLayout chatlay;
    EditText insertmsg;
    FloatingActionButton sendmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__conversation);

        chatlay=findViewById(R.id.chatlay);

        intent=getIntent();
        patientID=intent.getStringExtra("PatientID");
        doctorid= RetrofitURL1.DoctorID;
        patientname=intent.getStringExtra("PatientName");

        TextView name=findViewById(R.id.username);
        name.setText(patientname);

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Toast.makeText(getApplicationContext(),patientID, Toast.LENGTH_LONG).show();

        recyclerView=findViewById(R.id.recycler_view_msg);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        chatList=new ArrayList<>();
        recyclerViewAdapter=new ConversationAdapter(getApplicationContext(),chatList);
        recyclerView.setAdapter(recyclerViewAdapter);
            //content();

        readMessage();


        insertmsg=findViewById(R.id.insert_msg);
        sendmsg=findViewById(R.id.btn_send);
        //recyclerView.notify();
        chatList=new ArrayList<>();
        intent=getIntent();
        //userid=intent.getStringExtra("uid");


        final String sendertype="Doctor";

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=insertmsg.getText().toString();
                if (TextUtils.isEmpty(msg)){
                    Snackbar.make(v,"Type message...",Snackbar.LENGTH_LONG).show();
                }else if (!TextUtils.isEmpty(msg)){
                    sendMessage(doctorid, patientID, msg, sendertype);
                    insertmsg.setText(null);
                }

            }
        });
        readMessage();

    }

    private void sendMessage(String sender, final String receiver, String message,String sendertype)
    {

        SimpleDateFormat stf = new SimpleDateFormat("h:mm a");
        String currentTime = stf.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = sdf.format(new Date());


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("doctorid",sender);
        hashMap.put("patientid",receiver);
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
                    chatRef.child("id").setValue(receiver);
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
                    if (chat.getDoctorid().equals(doctorid) && chat.getPatientid().equals(patientID)){
                        chatList.add(chat);
                    }

                    //chatList.add(chat);
                }

                recyclerViewAdapter= new ConversationAdapter(getApplicationContext(), chatList);

                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"fail"+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
