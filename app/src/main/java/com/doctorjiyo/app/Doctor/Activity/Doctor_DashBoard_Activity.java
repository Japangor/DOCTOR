package com.doctorjiyo.app.Doctor.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.BuildConfig;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.Doctor.Fragments.Doc_Patients_Fragment;
import com.doctorjiyo.app.Fragments.Appointment_db_Fragment;
import com.doctorjiyo.app.SplashScreenActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import com.doctorjiyo.app.Doctor.Fragments.Doc_Home_Fragment;
import com.doctorjiyo.app.Doctor.Fragments.Doc_PatientProfileList_Fragment;
import com.doctorjiyo.app.Fragments.HealthArtcle_db_Fragment;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.utils.Tools;
import com.doctorjiyo.app.utils.ViewAnimation;

public class Doctor_DashBoard_Activity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ActionBar actionBar;
    Toolbar toolbar;
    LinearLayout bottom_navigation;

    FragmentTransaction fragmentTransaction;
    NestedScrollView nested_scroll_view;
    LinearLayout fragment_container_layout;

    Bundle bundle2;

    ImageView home_iv,patients_iv,profile_iv;
    TextView home_tv,patients_tv,profile_tv;
    LinearLayout home_lay,patients_lay,profile_lay;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dash_board);

        @SuppressWarnings("WrongConstant")
        SharedPreferences sharedPreferences=getSharedPreferences("AllDataUser",MODE_APPEND);
        userid=sharedPreferences.getString("DoctorID","");

        initToolbar();
        initComponent();
        initNavigationMenu();

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        final Appointment_db_Fragment appointment_db_fragment=new Appointment_db_Fragment();
        bundle2=new Bundle();
        bundle2.putString("userid",userid);

        appointment_db_fragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,appointment_db_fragment);
        getSupportFragmentManager().beginTransaction().commit();
        setFragment(appointment_db_fragment);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_20);
    }

    private void initComponent() {

        fragment_container_layout=findViewById(R.id.fragment_container_layout);

        home_lay=findViewById(R.id.lay_home);
        patients_lay=findViewById(R.id.lay_patients);
        profile_lay=findViewById(R.id.lay_profile);

        home_iv=findViewById(R.id.iv_home);
        patients_iv=findViewById(R.id.iv_patients);
        profile_iv=findViewById(R.id.iv_profile);

        home_tv=findViewById(R.id.tv_home);
        patients_tv=findViewById(R.id.tv_patients);
        profile_tv=findViewById(R.id.tv_profile);


        home_iv.setColorFilter(getResources().getColor(R.color.white));
        patients_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

        home_tv.setTextColor(getResources().getColor(R.color.white));
        patients_tv.setTextColor(getResources().getColor(R.color.green_dark1));
        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

        home_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home_iv.setColorFilter(getResources().getColor(R.color.white));
                patients_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                home_tv.setTextColor(getResources().getColor(R.color.white));
                patients_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


                actionBar.setTitle("Home");
                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                final Appointment_db_Fragment appointment_db_fragment=new Appointment_db_Fragment();
                bundle2=new Bundle();
                bundle2.putString("userid",userid);

                appointment_db_fragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,appointment_db_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(appointment_db_fragment);
            }
        });


        patients_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patients_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                patients_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));


                actionBar.setTitle("Patients");
                //Toast.makeText(getApplicationContext(), "Appointment",Toast.LENGTH_SHORT).show();
                final Doc_Patients_Fragment doc_patients_fragment=new Doc_Patients_Fragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,doc_patients_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(doc_patients_fragment);

            }
        });



        profile_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_iv.setColorFilter(getResources().getColor(R.color.white));
                home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                patients_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                profile_tv.setTextColor(getResources().getColor(R.color.white));
                home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                patients_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                actionBar.setTitle("Profile");
                //Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                final Doc_PatientProfileList_Fragment doc_patientProfileList_fragment=new Doc_PatientProfileList_Fragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,doc_patientProfileList_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(doc_patientProfileList_fragment);

            }
        });

        ViewAnimation.fadeOutIn(fragment_container_layout);


        bottom_navigation=findViewById(R.id.bottom_navigation);

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
                        patients_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.white));
                        patients_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Doc_Home_Fragment doc_home_fragment=new Doc_Home_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,doc_home_fragment).commit();
                        setFragment(doc_home_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);
                        break;

                    case R.id.nav_patients:
                        Toast.makeText(getApplicationContext(), "Patients", Toast.LENGTH_SHORT).show();

                        patients_iv.setColorFilter(getResources().getColor(R.color.white));
                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        patients_tv.setTextColor(getResources().getColor(R.color.white));
                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final Doc_Patients_Fragment doc_patients_fragment=new Doc_Patients_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,doc_patients_fragment).commit();
                        setFragment(doc_patients_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);
                        break;

                    case R.id.nav_healthArticles:
                        Toast.makeText(getApplicationContext(), "Health Articles", Toast.LENGTH_SHORT).show();

                        home_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        patients_iv.setColorFilter(getResources().getColor(R.color.green_dark1));
                        profile_iv.setColorFilter(getResources().getColor(R.color.green_dark1));

                        home_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        patients_tv.setTextColor(getResources().getColor(R.color.green_dark1));
                        profile_tv.setTextColor(getResources().getColor(R.color.green_dark1));

                        final HealthArtcle_db_Fragment healthArtcle_db_fragment=new HealthArtcle_db_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthArtcle_db_Fragment()).commit();
                        setFragment(healthArtcle_db_fragment);
                        bottom_navigation.setVisibility(View.VISIBLE);

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
