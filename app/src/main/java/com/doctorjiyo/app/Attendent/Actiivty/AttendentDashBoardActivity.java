package com.doctorjiyo.app.Attendent.Actiivty;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.Attendent.Fragment.Attendent_AddPatient_Fragment;
import com.doctorjiyo.app.Attendent.Fragment.Attendent_Profile_Fragment;
import com.doctorjiyo.app.Attendent.Fragment.Attendent_ViewAppointment_Fragment;
import com.doctorjiyo.app.Doctor.Fragments.Doc_Home_Fragment;
import com.doctorjiyo.app.Doctor.Fragments.Doc_Patients_Fragment;
import com.doctorjiyo.app.Fragments.HealthArtcle_db_Fragment;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.SplashScreenActivity;
import com.google.android.material.navigation.NavigationView;

public class AttendentDashBoardActivity extends AppCompatActivity{

    private ActionBar actionBar;
    Toolbar toolbar;

    String userid;

    ImageView iv_appointment,iv_AddPatient,iv_profile;
    TextView tv_appointment,tv_AddPatient,tv_profile;
    LinearLayout lay_appointment,lay_AddPatient,lay_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendent_dash_board);

        @SuppressWarnings("WrongConstant")
        SharedPreferences sharedPreferences=getSharedPreferences("AllDataUser",MODE_APPEND);
        userid=sharedPreferences.getString("AttendantID","");

        initToolbar();
        initBottomNavigationComponents();
        setBottomNavEvent();
        initNavigationMenu();


    }

    private void initToolbar() {
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_attendent);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.action_logout){
                    logout();
                }
                return false;

            }
        });
    }

    void initBottomNavigationComponents(){
        iv_appointment=findViewById(R.id.iv_appointment);
        tv_appointment=findViewById(R.id.tv_appointment);
        lay_appointment=findViewById(R.id.lay_appointment);

        iv_AddPatient=findViewById(R.id.iv_AddPatient);
        tv_AddPatient=findViewById(R.id.tv_AddPatient);
        lay_AddPatient=findViewById(R.id.lay_AddPatient);

        iv_profile=findViewById(R.id.iv_profile);
        tv_profile=findViewById(R.id.tv_profile);
        lay_profile=findViewById(R.id.lay_profile);

        iv_appointment.setColorFilter(getResources().getColor(R.color.white));
        iv_AddPatient.setColorFilter(getResources().getColor(R.color.green_dark1));
        iv_profile.setColorFilter(getResources().getColor(R.color.green_dark1));

        tv_appointment.setTextColor(getResources().getColor(R.color.white));
        tv_AddPatient.setTextColor(getResources().getColor(R.color.green_dark1));
        tv_profile.setTextColor(getResources().getColor(R.color.green_dark1));


        toolbar.setTitle("Appointments");
        //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
        final Attendent_ViewAppointment_Fragment attendent_viewAppointment_fragment=new Attendent_ViewAppointment_Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,attendent_viewAppointment_fragment);
        getSupportFragmentManager().beginTransaction().commit();
        setFragment(attendent_viewAppointment_fragment);

    }

    void setBottomNavEvent(){
        iv_appointment.setColorFilter(getResources().getColor(R.color.white));
        iv_AddPatient.setColorFilter(getResources().getColor(R.color.green_dark1));
        iv_profile.setColorFilter(getResources().getColor(R.color.green_dark1));

        tv_appointment.setTextColor(getResources().getColor(R.color.white));
        tv_AddPatient.setTextColor(getResources().getColor(R.color.green_dark1));
        tv_profile.setTextColor(getResources().getColor(R.color.green_dark1));

        lay_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_appointment.setColorFilter(getResources().getColor(R.color.white));
                iv_AddPatient.setColorFilter(getResources().getColor(R.color.green_dark1));
                iv_profile.setColorFilter(getResources().getColor(R.color.green_dark1));

                tv_appointment.setTextColor(getResources().getColor(R.color.white));
                tv_AddPatient.setTextColor(getResources().getColor(R.color.green_dark1));
                tv_profile.setTextColor(getResources().getColor(R.color.green_dark1));


                toolbar.setTitle("Appointments");
                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                final Attendent_ViewAppointment_Fragment attendent_viewAppointment_fragment=new Attendent_ViewAppointment_Fragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,attendent_viewAppointment_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(attendent_viewAppointment_fragment);
            }
        });


        lay_AddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_AddPatient.setColorFilter(getResources().getColor(R.color.white));
                iv_appointment.setColorFilter(getResources().getColor(R.color.green_dark1));
                iv_profile.setColorFilter(getResources().getColor(R.color.green_dark1));

                tv_AddPatient.setTextColor(getResources().getColor(R.color.white));
                tv_appointment.setTextColor(getResources().getColor(R.color.green_dark1));
                tv_profile.setTextColor(getResources().getColor(R.color.green_dark1));


                toolbar.setTitle("Patients");
                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                final Attendent_AddPatient_Fragment attendent_addPatient_fragment=new Attendent_AddPatient_Fragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,attendent_addPatient_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(attendent_addPatient_fragment);
            }
        });


        lay_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_profile.setColorFilter(getResources().getColor(R.color.white));
                iv_appointment.setColorFilter(getResources().getColor(R.color.green_dark1));
                iv_AddPatient.setColorFilter(getResources().getColor(R.color.green_dark1));

                tv_profile.setTextColor(getResources().getColor(R.color.white));
                tv_appointment.setTextColor(getResources().getColor(R.color.green_dark1));
                tv_AddPatient.setTextColor(getResources().getColor(R.color.green_dark1));


                toolbar.setTitle("Profile");
                //Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                final Attendent_Profile_Fragment attendent_profile_fragment=new Attendent_Profile_Fragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,attendent_profile_fragment);
                getSupportFragmentManager().beginTransaction().commit();
                setFragment(attendent_profile_fragment);
            }
        });


    }
    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view2);
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

                        final Attendent_ViewAppointment_Fragment attendent_viewAppointment_fragment=new Attendent_ViewAppointment_Fragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,attendent_viewAppointment_fragment);
                        getSupportFragmentManager().beginTransaction().commit();
                        setFragment(attendent_viewAppointment_fragment);

                        break;

                    case R.id.nav_patients:
                        Toast.makeText(getApplicationContext(), "Patients", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.nav_healthArticles:
                        Toast.makeText(getApplicationContext(), "Health Articles", Toast.LENGTH_SHORT).show();



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

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();

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

}
