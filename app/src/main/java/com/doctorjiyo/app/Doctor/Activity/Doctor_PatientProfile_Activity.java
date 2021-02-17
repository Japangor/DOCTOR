package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmentActivity;
import com.google.android.material.tabs.TabLayout;

import com.doctorjiyo.app.API.Model.ResponsePatientProfile;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Fragments.Medical_Profile_Fragment;
import com.doctorjiyo.app.Fragments.Personal_profile_Fragment;
import com.doctorjiyo.app.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class Doctor_PatientProfile_Activity extends AppCompatActivity {

    TextView patientname,patientlocation;
    CircleImageView profile_pic;
    ImageView close;

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    FragmentActivity fragmentActivity;
    String patientID,ImagePath,AttendentId;
    Intent intent;

    LinearLayout lay_chat,lay_appointment,lay_prescription,lay_medicalrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__patient_profile_);

        intent=getIntent();
        patientID=intent.getStringExtra("patientID");
        ImagePath=intent.getStringExtra("ImagePath");
        AttendentId=intent.getStringExtra("AttendentId");



        Toast.makeText(getApplicationContext(),AttendentId, Toast.LENGTH_LONG).show();

        initComponets();

            lay_chat.setVisibility(View.VISIBLE);
            lay_appointment.setVisibility(View.VISIBLE);
            lay_prescription.setVisibility(View.VISIBLE);
            lay_medicalrecord.setVisibility(View.VISIBLE);


        //Toast.makeText(getApplicationContext(),ImagePath,Toast.LENGTH_LONG).show();

        if (ImagePath.equals("")){
            Picasso.with(getApplicationContext()).load(R.drawable.img_applogo).into(profile_pic);
        }else{
            Picasso.with(getApplicationContext()).load(ImagePath).placeholder(R.drawable.img_applogo).into(profile_pic);
        }


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        readProfileData();

        ClickEvents();

        fragment = new Personal_profile_Fragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("userid",patientID);
        fragment.setArguments(bundle1);
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Personal_profile_Fragment();
                        Bundle bundle2=new Bundle();
                        bundle2.putString("userid",patientID);
                        fragment.setArguments(bundle2);
                        break;
                    case 1:
                        fragment = new Medical_Profile_Fragment();
                        Bundle bundle3=new Bundle();
                        bundle3.putString("userid",patientID);
                        fragment.setArguments(bundle3);
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initComponets() {
        close=findViewById(R.id.close);
        profile_pic=findViewById(R.id.image);
        patientname=findViewById(R.id.patientname);
        patientlocation=findViewById(R.id.location);

        lay_chat=findViewById(R.id.chats);
        lay_appointment=findViewById(R.id.appointment);
        lay_prescription=findViewById(R.id.prescription);
        lay_medicalrecord=findViewById(R.id.medicalrecord);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);

    }

    void ClickEvents(){

        lay_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Doctor_Conversation_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PatientID",patientID);
                intent.putExtra("PatientName",patientname.getText().toString());
                startActivity(intent);
            }
        });

        lay_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), BookAppointmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PatientID",patientID);
                intent.putExtra("PatientName",patientname.getText().toString());
                startActivity(intent);
            }
        });

        lay_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Doctor_Prescription_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PatientID",patientID);
                intent.putExtra("PatientName",patientname.getText().toString());
                startActivity(intent);
            }
        });

        lay_medicalrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Doctor_MedicalRecords_List_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("PatientID",patientID);
                intent.putExtra("PatientName",patientname.getText().toString());
                startActivity(intent);
            }
        });

    }

    public void readProfileData(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponsePatientProfile> call=service.getPatientProfile(
                patientID
        );
        call.enqueue(new Callback<ResponsePatientProfile>() {
            @Override
            public void onResponse(Call<ResponsePatientProfile> call, Response<ResponsePatientProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){

                        patientname.setText(response.body().getPatientName().trim());
                        patientlocation.setText(response.body().getLocation().trim());
                        String imagePath=response.body().getImgPath();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePatientProfile> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}
