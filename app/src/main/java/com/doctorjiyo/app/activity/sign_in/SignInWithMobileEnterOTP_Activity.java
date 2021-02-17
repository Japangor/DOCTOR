package com.doctorjiyo.app.activity.sign_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInWithMobileEnterOTP_Activity extends AppCompatActivity {



    AppCompatButton getotp_btn,verifyotp_btn;
    RelativeLayout phone_layout;
    LinearLayout otp_layout;
    TextInputEditText phoneOTP,otp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_mobile_enter_otp);

        verifyotp_btn=findViewById(R.id.signin_with_otp);

        otp=findViewById(R.id.otp);

        verifyotp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP();

            }
        });

    }

    private void verifyOTP(){


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing...");
        progressDialog.show();

        String sOTP=otp.getText().toString().trim();


        APIService service =RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call = service.LoginverifyOTP(sOTP);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.code()==200) {
                    if (response.body().getSuccess()) {
                        String userid=response.body().getMessage().trim();
                        //Intent intent=new Intent(getApplicationContext(),Dashboard_Activity.class);
                       // Bundle bundle=new Bundle();
                        //bundle.putString("username",username);
                       // bundle.putString("userid", userid);
                       // intent.putExtras(bundle);
                       // startActivity(intent);
                       // finish();

                        Toast.makeText(getApplicationContext(), "SignIn Success with "+userid, Toast.LENGTH_SHORT).show();
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

}
