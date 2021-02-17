package com.doctorjiyo.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.doctorjiyo.app.API.Model.IntroHeadingModel;
import com.doctorjiyo.app.API.RetrofitURL;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView appLogo;
    ProgressBar progress_circular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!amIConnected(SplashScreenActivity.this)) buildDialog(SplashScreenActivity.this).show();
        else {


            setContentView(R.layout.activity_splash_screen);
            progress_circular=findViewById(R.id.progress);
            appLogo=(ImageView)findViewById(R.id.imageView);
            Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
            appLogo.startAnimation(myanim);




            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    progress_circular.setVisibility(View.VISIBLE);
                    openIntroActivity();
                    /*
                    Intent i = new Intent(SplashScreenActivity.this, IntroSlideActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                     */
                }
            }, 2000);
        }
    }

    public void openIntroActivity(){


        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<IntroHeadingModel> call=service.getIntroSlide();
        call.enqueue(new Callback<IntroHeadingModel>() {
            @Override
            public void onResponse(Call<IntroHeadingModel> call, Response<IntroHeadingModel> response) {
                if (response.code()==201||response.code()==200){
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

                        //Toast.makeText(getApplicationContext(),HeadingSlide1,Toast.LENGTH_LONG).show();

                        Intent i = new Intent(SplashScreenActivity.this, IntroSlideActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("IconSlide1",IconSlide1);
                        i.putExtra("IconSlide2",IconSlide2);
                        i.putExtra("IconSlide3",IconSlide3);
                        i.putExtra("HeadingSlide1",HeadingSlide1);
                        i.putExtra("HeadingSlide2",HeadingSlide2);
                        i.putExtra("HeadingSlide3",HeadingSlide3);
                        i.putExtra("TextSlide1",TextSlide1);
                        i.putExtra("TextSlide2",TextSlide2);
                        i.putExtra("TextSlide3",TextSlide3);
                        i.putExtra("BgColorSlide1",BgColorSlide1);
                        i.putExtra("BgColorSlide2",BgColorSlide2);
                        i.putExtra("BgColorSlide3",BgColorSlide3);
                        startActivity(i);
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<IntroHeadingModel> call, Throwable t) {

            }
        });
    }

    public boolean amIConnected(Context context)
    {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
            {
                return true;
            }

            else
            {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


}

