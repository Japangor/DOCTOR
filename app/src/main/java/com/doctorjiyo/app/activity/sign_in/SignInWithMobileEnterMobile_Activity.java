package com.doctorjiyo.app.activity.sign_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.dashboard.Dashboard_Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInWithMobileEnterMobile_Activity extends AppCompatActivity {


    AppCompatButton getotp_btn,verifyotp_btn;
    RelativeLayout phone_layout;
    LinearLayout otp_layout;
    TextInputEditText phoneOTP,otp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_mobile_enter_mobile);


        initComponents();
        clickListeners();

    }


    public void initComponents(){

        getotp_btn=findViewById(R.id.getOTP);
        phone_layout=findViewById(R.id.phone_verfication);

        phoneOTP=findViewById(R.id.phone);


    }


    private void clickListeners() {

        getotp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOTP();

            }
        });

    }


    private void requestOTP(){
        APIService service =
                RetrofitURL1.getClient().create(APIService.class);

        Call<Result> call = service.LoginsentOTP(
                phoneOTP.getText().toString()
        );
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //sessionId = response.body().getSucccess();
                //Log.d("SenderID", sessionId);
                //you may add code to automatically fetch OTP from messages.
                if (response.code()==200) {
                    if (response.body().getSuccess()) {
                        finish();
                        //SharedPreferences.Editor editor = sharedpreferences.edit();
                       // editor.putString(uid, response.body().getMessage().trim());
                      //  editor.commit();
                        Toast.makeText(getApplicationContext(),"send otp",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SignInWithMobileEnterOTP_Activity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Server error please try again later!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("ERROR", t.toString());

                Toast.makeText(getApplicationContext(),t.getMessage()+" Failed ",Toast.LENGTH_LONG).show();
            }

        });
    }

    private void verifyOTP(){
        APIService apiService =
                RetrofitURL1.getClient().create(APIService.class);


        Call<Result> call = apiService.LoginverifyOTP(
                otp.getText().toString().trim());

        call.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.code()==200) {
                    if (response.body().getSuccess()) {
                        finish();
                        // SharedPreferences.Editor editor = sharedpreferences.edit();
                        // editor.putString(uid, response.body().getMessage().trim());
                        //  editor.commit();
                        // startActivity(new Intent(getApplicationContext(), Dashboard_Activity.class).putExtra("username", response.body().getMessage().trim()));
                        Toast.makeText(getApplicationContext(), "verify", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Dashboard_Activity.class));//.putExtra("MobileNumber",response.body().getMessage().trim()));

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Server error please try again later!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }

        });
    }


}
