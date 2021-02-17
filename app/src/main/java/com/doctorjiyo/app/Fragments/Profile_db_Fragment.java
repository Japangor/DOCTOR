package com.doctorjiyo.app.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import com.doctorjiyo.app.API.Model.ResponsePatientProfile;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Profile.EditProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.squareup.picasso.Picasso;

public class Profile_db_Fragment extends Fragment {

    TextView editProfile,patientname,patientlocation;
    CircleImageView profile_pic;

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    FragmentActivity fragmentActivity;
    String patientID;
    Intent intent1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile_db, container, false);

        patientID=this.getArguments().getString("userid");
        Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();

        patientname=view.findViewById(R.id.patientname);
        patientlocation=view.findViewById(R.id.location);
        profile_pic=view.findViewById(R.id.profile_pic);



        readProfileData();

        editProfile=view.findViewById(R.id.edit_profile);




        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        frameLayout=(FrameLayout)view.findViewById(R.id.frameLayout);
        fragment = new Personal_profile_Fragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("userid",patientID);
        fragment.setArguments(bundle1);
        fragmentManager =fragmentActivity.getSupportFragmentManager();
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
                FragmentManager fm = fragmentActivity.getSupportFragmentManager();
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



        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public void readProfileData(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponsePatientProfile> call=service.getPatientProfile(
                patientID
        );
        call.enqueue(new Callback<ResponsePatientProfile>() {
            @Override
            public void onResponse(Call<ResponsePatientProfile> call, final Response<ResponsePatientProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){

                        patientname.setText(response.body().getPatientName().trim());
                        patientlocation.setText(response.body().getLocation().trim());
                        if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                            Picasso.with(getContext().getApplicationContext()).load(R.drawable.img_applogo).into(profile_pic);
                        }else {
                            Picasso.with(getContext().getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.img_applogo).into(profile_pic);

                        }

                        editProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getActivity(),EditProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("PatientID",patientID);
                                intent.putExtra("PatientName",response.body().getPatientName());
                                intent.putExtra("ContactNumber",response.body().getContactNumber());
                                intent.putExtra("Email",response.body().getEmail());
                                intent.putExtra("Gender",response.body().getGender());
                                intent.putExtra("DOB",response.body().getDOB());
                                intent.putExtra("BloodGroup",response.body().getBloodGroup());
                                intent.putExtra("MaritalStatus",response.body().getMaritalStatus());
                                intent.putExtra("Height",response.body().getHeight());
                                intent.putExtra("Weight",response.body().getWeight());
                                intent.putExtra("EmergencyContact",response.body().getEmergencyContact());
                                intent.putExtra("Location",response.body().getLocation());
                                intent.putExtra("SmokingHabits",response.body().getSmokingHabits());
                                intent.putExtra("AlcoholConsumption",response.body().getAlcoholConsumption());
                                intent.putExtra("FoodPreferences",response.body().getFoodPreferences());
                                intent.putExtra("patientphoto",response.body().getImgPath().trim());
                                startActivity(intent);

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePatientProfile> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }




}
