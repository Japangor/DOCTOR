package com.doctorjiyo.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.doctorjiyo.app.Attendent.Actiivty.AttendentDashBoardActivity;
import com.doctorjiyo.app.Doctor.Activity.Doctor_DashBoard_Activity;
import com.doctorjiyo.app.activity.dashboard.Dashboard_Activity;
import com.google.android.material.tabs.TabLayout;
import com.karan.churi.PermissionManager.PermissionManager;
import com.doctorjiyo.app.activity.sign_in.SignIn_Activity;

import java.util.ArrayList;
import java.util.List;

public class IntroSlideActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;

    PermissionManager permissionManager;
    Intent intent;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);

        sharedPreferences=getSharedPreferences("AllDataUser",MODE_PRIVATE);
        checkLoggedIn();
        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openened before or not
/*
        if (restorePrefData()) {

            Intent signinActivity = new Intent(getApplicationContext(), SignIn_Activity.class );
            startActivity(signinActivity);
            finish();


        }
*/
        setContentView(R.layout.activity_introslide);
        // hide the action bar

//        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen

/*
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Fresh Food","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit","f0f0"));
        mList.add(new ScreenItem("Fast Delivery","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit","f0f0"));
        mList.add(new ScreenItem("Easy Payment","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit","f0f0"));
*/
        intent=getIntent();
        String HeadingSlide1=intent.getStringExtra("HeadingSlide1");
        String HeadingSlide2=intent.getStringExtra("HeadingSlide2");
        String HeadingSlide3=intent.getStringExtra("HeadingSlide3");
        String TextSlide1=intent.getStringExtra("TextSlide1");
        String TextSlide2=intent.getStringExtra("TextSlide2");
        String TextSlide3=intent.getStringExtra("TextSlide3");
        String IconSlide1=intent.getStringExtra("IconSlide1");
        String IconSlide2=intent.getStringExtra("IconSlide2");
        String IconSlide3=intent.getStringExtra("IconSlide3");
        String BgColorSlide1=intent.getStringExtra("BgColorSlide1");
        String BgColorSlide2=intent.getStringExtra("BgColorSlide2");
        String BgColorSlide3=intent.getStringExtra("BgColorSlide3");

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem(HeadingSlide1,TextSlide1,IconSlide1,BgColorSlide1));
        mList.add(new ScreenItem(HeadingSlide2,TextSlide2,IconSlide2,BgColorSlide2));
        mList.add(new ScreenItem(HeadingSlide3,TextSlide3,IconSlide3,BgColorSlide3));



        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(getApplicationContext(),mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }
                if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }
            }
        });

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // skip button click listener
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });
/*
        APIService service= RetrofitURL.getClient().create(APIService.class);
        Call<IntroHeadingModel> call=service.getIntroSlide();
        call.enqueue(new Callback<IntroHeadingModel>() {
            @Override
            public void onResponse(Call<IntroHeadingModel> call, Response<IntroHeadingModel> response) {
                if (response.code()==201 || response.code()==200){
                    if (response.body().getSuccess()){
                        String IconSlide1,IconSlide2,IconSlide3,HeadingSlide1,HeadingSlide2,HeadingSlide3,TextSlide1,TextSlide2,TextSlide3,BgColorSlide1,BgColorSlide2,BgColorSlide3;

                        IconSlide1=response.body().getIconSlide1();
                        IconSlide2=response.body().getIconSlide2();
                        IconSlide3=response.body().getIconSlide3();
                        HeadingSlide1=response.body().getHeadingSlide1();
                        HeadingSlide2=response.body().getHeadingSlide2();
                        HeadingSlide3=response.body().getHeadingSlide3();
                        TextSlide1=response.body().getTextSlide1();
                        TextSlide2=response.body().getTextSlide2();
                        TextSlide3=response.body().getTextSlide3();
                        BgColorSlide1=response.body().getBgColorSlide1();
                        BgColorSlide2=response.body().getBgColorSlide2();
                        BgColorSlide3=response.body().getBgColorSlide3();

                        //Toast.makeText(getApplicationContext(),"kfkvkhg"+HeadingSlide1,Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<IntroHeadingModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

*/

        // Get Started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity
                Intent mainActivity = new Intent(getApplicationContext(), SignIn_Activity.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                //savePrefsData();
                finish();
            }
        });


    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }

    void checkLoggedIn() {
        @SuppressWarnings("WrongConstant")
        SharedPreferences preferences = getSharedPreferences("AllDataUser", MODE_APPEND);


        if (preferences.getString("PatientID","")!=""){
            startActivity(new Intent(getApplicationContext(), Dashboard_Activity.class));
            finish();
        }
        else if (preferences.getString("DoctorID","")!=""){
            startActivity(new Intent(getApplicationContext(), Doctor_DashBoard_Activity.class));
            finish();
        }
        else if (preferences.getString("AttendantID","")!=""){
            startActivity(new Intent(getApplicationContext(), AttendentDashBoardActivity.class));
            finish();
        }
        else {

        }

    }
}
