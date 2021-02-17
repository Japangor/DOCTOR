package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doctorjiyo.app.R;

public class Doctor_Appointment_Activity extends AppCompatActivity {

    ImageButton close;
    Intent intent;
    String s_appointmentid,s_patientname,s_patientcontact,s_patientemail,s_date,s_doctorname,s_purpose;
    TextView tv_patientname,tv_patientcontact,tv_patientemail,tv_date,tv_doctorname,tv_purpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__appointment);

        intent=getIntent();
        s_appointmentid=intent.getStringExtra("appointmentid");
        s_patientname=intent.getStringExtra("patientname");
        s_patientcontact=intent.getStringExtra("patientcontact");
        s_patientemail=intent.getStringExtra("patientemail");
        s_date=intent.getStringExtra("date");
        s_doctorname=intent.getStringExtra("doctorname");
        s_purpose=intent.getStringExtra("purpose");

        tv_doctorname=findViewById(R.id.doctorname);
        tv_purpose=findViewById(R.id.purpose);
        tv_date=findViewById(R.id.date);
        tv_patientname=findViewById(R.id.patientname);
        tv_patientcontact=findViewById(R.id.patientcontact);
        tv_patientemail=findViewById(R.id.patientemail);

        tv_doctorname.setText(s_doctorname);
        tv_purpose.setText(s_purpose);
        tv_date.setText(s_date);
        tv_patientname.setText(s_patientname);
        tv_patientcontact.setText(s_patientcontact);
        tv_patientemail.setText(s_patientemail);




        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
