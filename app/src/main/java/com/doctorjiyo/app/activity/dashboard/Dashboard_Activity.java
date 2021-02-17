package com.doctorjiyo.app.activity.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.API.Model.ResponsePatientProfile;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.SplashScreenActivity;
import com.doctorjiyo.app.activity.Notification.PatientNotificationActivity;
import com.doctorjiyo.app.activity.Profile.EditProfileActivity;
import com.doctorjiyo.app.adapter.PrescriptionListAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import com.doctorjiyo.app.BuildConfig;
import com.doctorjiyo.app.Fragments.Appointment_db_Fragment;
import com.doctorjiyo.app.Fragments.Chats_db_Fragment;
import com.doctorjiyo.app.Fragments.HealthArtcle_db_Fragment;
import com.doctorjiyo.app.Fragments.Home_db_Fragment;
import com.doctorjiyo.app.Fragments.MedicalReport_db_Fragment;
import com.doctorjiyo.app.Fragments.MyDoctorFragment;
import com.doctorjiyo.app.Fragments.Prescriptions_db_Fragment;
import com.doctorjiyo.app.Fragments.Profile_db_Fragment;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.sign_in.SignIn_Activity;
import com.doctorjiyo.app.utils.Tools;
import com.doctorjiyo.app.utils.ViewAnimation;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard_Activity extends AppCompatActivity {


    private TabLayout tab_layout;
    private ActionBar actionBar;
    Toolbar toolbar;
    LinearLayout bottom_navigation;

    FragmentTransaction fragmentTransaction;
    NestedScrollView nested_scroll_view;
    LinearLayout fragment_container_layout;

    Bundle bundle2;
    String userid;
    Intent intent;
    Boolean FragmentString=false;



    ImageView home_iv,appointment_iv,records_iv,chats_iv,profile_iv;
    TextView home_tv,appointment_tv,records_tv,chats_tv,profile_tv;
    LinearLayout home_lay,appointment_lay,records_lay,chats_lay,profile_lay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        @SuppressWarnings("WrongConstant")
        SharedPreferences sharedPreferences=getSharedPreferences("AllDataUser",MODE_APPEND);
        userid=sharedPreferences.getString("PatientID","");

        initToolbar();
        initComponent();
        initNavigationMenu();



        intent=getIntent();
        FragmentString=intent.getBooleanExtra("Fragment",false);
        Toast.makeText(getApplicationContext(),"Fragment : "+FragmentString.toString(),Toast.LENGTH_LONG).show();


        if (FragmentString==true){
            appointment_iv.setColorFilter(getResources().getColor(R.color.white));
            home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

            appointment_tv.setTextColor(getResources().getColor(R.color.white));
            home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


            actionBar.setTitle("Appointments");
            //Toast.makeText(getApplicationContext(), "Appointment",Toast.LENGTH_SHORT).show();
            final Appointment_db_Fragment appointment_db_fragment=new Appointment_db_Fragment();
            bundle2=new Bundle();
            bundle2.putString("userid",userid);

            appointment_db_fragment.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,appointment_db_fragment);
            getSupportFragmentManager().beginTransaction().commit();
            setFragment(appointment_db_fragment);
        }else {
            home_iv.setColorFilter(getResources().getColor(R.color.white));
            appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
            profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

            home_tv.setTextColor(getResources().getColor(R.color.white));
            appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
            profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


            actionBar.setTitle("Home");
            //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
            final Home_db_Fragment home_db_fragment=new Home_db_Fragment();
            bundle2=new Bundle();
            bundle2.putString("userid",userid);
            home_db_fragment.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,home_db_fragment);
            getSupportFragmentManager().beginTransaction().commit();
            setFragment(home_db_fragment);
        }


    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        Tools.setSystemBarColor(this, R.color.grey_20);
    }



    private void initComponent() {

        fragment_container_layout=findViewById(R.id.fragment_container_layout);

        home_lay=findViewById(R.id.lay_home);
        appointment_lay=findViewById(R.id.lay_appointment);
        records_lay=findViewById(R.id.lay_records);
        chats_lay=findViewById(R.id.lay_chat);
        profile_lay=findViewById(R.id.lay_profile);

        home_iv=findViewById(R.id.iv_home);
        appointment_iv=findViewById(R.id.iv_appointment);
        records_iv=findViewById(R.id.iv_records);
        chats_iv=findViewById(R.id.iv_chat);
        profile_iv=findViewById(R.id.iv_profile);

        home_tv=findViewById(R.id.tv_home);
        appointment_tv=findViewById(R.id.tv_appointment);
        records_tv=findViewById(R.id.tv_records);
        chats_tv=findViewById(R.id.tv_chat);
        profile_tv=findViewById(R.id.tv_profile);


        home_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home_iv.setColorFilter(getResources().getColor(R.color.white));
                appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                home_tv.setTextColor(getResources().getColor(R.color.white));
                appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


                actionBar.setTitle("Home");
                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                final Home_db_Fragment home_db_fragment=new Home_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);
                home_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,home_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(home_db_fragment);
            }
        });


        appointment_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                appointment_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


                actionBar.setTitle("Appointments");
                //Toast.makeText(getApplicationContext(), "Appointment",Toast.LENGTH_SHORT).show();
                final Appointment_db_Fragment appointment_db_fragment=new Appointment_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);

                appointment_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,appointment_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(appointment_db_fragment);

            }
        });

        records_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                records_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                records_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                actionBar.setTitle("Medical Records");
                //Toast.makeText(getApplicationContext(), "Medical Reports",Toast.LENGTH_SHORT).show();
                final MedicalReport_db_Fragment medicalReport_db_fragment= new MedicalReport_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);

                medicalReport_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,medicalReport_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(medicalReport_db_fragment);

            }
        });

        chats_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                chats_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                chats_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                actionBar.setTitle("Conversation");
                //Toast.makeText(getApplicationContext(), "Chats",Toast.LENGTH_SHORT).show();
                final Chats_db_Fragment chats_db_fragment=new Chats_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);

                chats_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,chats_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(chats_db_fragment);

            }
        });

        profile_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                profile_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                actionBar.setTitle("Profile");
                //Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                final Profile_db_Fragment profile_db_fragment=new Profile_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);

                profile_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profile_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(profile_db_fragment);

            }
        });

        ViewAnimation.fadeOutIn(fragment_container_layout);


        bottom_navigation=findViewById(R.id.bottom_navigation);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_patient_dashboard,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id==R.id.notification){
            Intent intent=new Intent(getApplicationContext(),PatientNotificationActivity.class);
            intent.putExtra("PatientID",userid);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        View headerview=nav_view.getHeaderView(0);

        final ImageView pt_pic=headerview.findViewById(R.id.pt_pic);
        final TextView pt_name=headerview.findViewById(R.id.pt_name);
        final TextView pt_contact=headerview.findViewById(R.id.pt_contact);

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponsePatientProfile> call=service.getPatientProfile(
                userid
        );
        call.enqueue(new Callback<ResponsePatientProfile>() {
            @Override
            public void onResponse(Call<ResponsePatientProfile> call, final Response<ResponsePatientProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){

                        pt_name.setText(response.body().getPatientName().trim());
                        pt_contact.setText(response.body().getContactNumber().trim());
                        if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                            Glide.with(getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(pt_pic);
                        }else {
                            Glide.with(getApplicationContext()).load(response.body().getImgPath()).into(pt_pic);

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePatientProfile> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {

                actionBar.setTitle(item.getTitle());

                switch (item.getItemId()){

                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();

                        home_iv.setColorFilter(getResources().getColor(R.color.white));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.white));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Home_db_Fragment home_db_fragment=new Home_db_Fragment();
                        home_db_fragment.setArguments(bundle2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,home_db_fragment).commit();
                        setFragment(home_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);


                        drawer.closeDrawer(Gravity.LEFT);

                        break;

                    case R.id.nav_doctor:

                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        Toast.makeText(getApplicationContext(), "My Doctor", Toast.LENGTH_SHORT).show();
                        final MyDoctorFragment myDoctorFragment=new MyDoctorFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyDoctorFragment()).commit();
                        setFragment(myDoctorFragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;

                    case R.id.nav_appointment:
                        //Toast.makeText(getApplicationContext(), "Appointemts", Toast.LENGTH_SHORT).show();

                        appointment_iv.setColorFilter(getResources().getColor(R.color.white));
                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        appointment_tv.setTextColor(getResources().getColor(R.color.white));
                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Appointment_db_Fragment appointment_db_fragment=new Appointment_db_Fragment();
                        appointment_db_fragment.setArguments(bundle2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,appointment_db_fragment).commit();
                        setFragment(appointment_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_medicalReports:
                        Toast.makeText(getApplicationContext(), "Medical Records", Toast.LENGTH_SHORT).show();

                        records_iv.setColorFilter(getResources().getColor(R.color.white));
                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        records_tv.setTextColor(getResources().getColor(R.color.white));
                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


                        final MedicalReport_db_Fragment medicalReport_db_fragment= new MedicalReport_db_Fragment();
                        medicalReport_db_fragment.setArguments(bundle2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,medicalReport_db_fragment).commit();
                        setFragment(medicalReport_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_chats:
                        Toast.makeText(getApplicationContext(), "Conversation", Toast.LENGTH_SHORT).show();

                        chats_iv.setColorFilter(getResources().getColor(R.color.white));
                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        chats_tv.setTextColor(getResources().getColor(R.color.white));
                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Chats_db_Fragment chats_db_fragment=new Chats_db_Fragment();
                        chats_db_fragment.setArguments(bundle2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,chats_db_fragment).commit();
                        setFragment(chats_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_prescription:
                        Toast.makeText(getApplicationContext(), "Prescriptions", Toast.LENGTH_SHORT).show();

                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Prescriptions_db_Fragment prescriptions_db_fragment=new Prescriptions_db_Fragment();
                        prescriptions_db_fragment.setArguments(bundle2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,prescriptions_db_fragment).commit();
                        setFragment(prescriptions_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_healthArticles:
                        Toast.makeText(getApplicationContext(), "Health Articles", Toast.LENGTH_SHORT).show();

                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        appointment_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        records_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        chats_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        appointment_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        records_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        chats_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final HealthArtcle_db_Fragment healthArtcle_db_fragment=new HealthArtcle_db_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthArtcle_db_Fragment()).commit();
                        setFragment(healthArtcle_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

                        drawer.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_share:
                        Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage= "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "Share Via"));

                        drawer.closeDrawer(Gravity.LEFT);

                        break;


                    case R.id.nav_signout:
                      logout();

                        break;
                    default:
                        //Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                        break;

                }

                drawer.closeDrawers();
                return true;
            }
        });
    }

    void logout(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Logout");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences setting=getApplicationContext().getSharedPreferences("AllDataUser", Context.MODE_PRIVATE);
                        setting.edit().clear().commit();
                        startActivity(new Intent(getApplicationContext(), SplashScreenActivity.class));
                        finish();
                    }
                });
        alertDialogBuilder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //alertDialog.dismiss();
                    }
                });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();

    }
}

