package com.doctorjiyo.app.activity.sign_in;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doctorjiyo.app.API.Model.ResultLoginResponse;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Attendent.Actiivty.AttendentDashBoardActivity;
import com.doctorjiyo.app.Doctor.Activity.Doctor_DashBoard_Activity;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.dashboard.Dashboard_Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn_Activity extends AppCompatActivity {

    Button signin,signin_with_mob,signin_with_google;
    TextInputEditText tv_username,tv_password;
    LoginButton loginButton;
    TextView signup_here;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sharedPreferences=getSharedPreferences("AllDataUser",MODE_PRIVATE);
        initComponent();

        loginMethods();
        //checkLoggedIn();

    }


    private void initComponent() {

        signin_with_mob=findViewById(R.id.signin_with_mob);
        loginButton = findViewById(R.id.login_button);
        signup_here=findViewById(R.id.signup_here);
        loginButton.setVisibility(View.GONE);
        signin_with_google=findViewById(R.id.signin_google);
        tv_username=findViewById(R.id.username);
        tv_password=findViewById(R.id.password);
        signin=findViewById(R.id.signin);

    }

    private void loginMethods() {

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                //startActivity(new Intent(getApplicationContext(),Dashboard_Activity.class));
            }
        });

        signin_with_mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInWithMobileEnterMobile_Activity.class));

            }
        });

        signin_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signup_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));

            }
        });
    }

    private void loginUser()
    {
        final String username,password;
        username=tv_username.getText().toString().trim();
        password=tv_password.getText().toString().trim();

        final SharedPreferences.Editor editor=sharedPreferences.edit();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Login..");
        progressDialog.show();

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResultLoginResponse> call=service.PatientLogIn(
                username,
                password
        );

        call.enqueue(new Callback<ResultLoginResponse>() {
            @Override
            public void onResponse(Call<ResultLoginResponse> call, Response<ResultLoginResponse> response) {

                if (response.code() == 200 || response.code() == 201) {
                    if (response.body().getSuccess().equals(true)) {

                        progressDialog.dismiss();



                        String PatientID=response.body().getPatientID();
                        Log.e("LogIn ID","PatientID : "+ PatientID);
                        editor.putString("PatientID",PatientID);
                        editor.commit();

                        Intent intent=new Intent(getApplicationContext(),Dashboard_Activity.class);
                        intent.putExtra("username",username);
                        intent.putExtra("PatientID", PatientID);
                        startActivity(intent);
                        finish();

                    }
                    else if (response.body().getSuccess().equals(false)){

                        final SharedPreferences.Editor editor1=sharedPreferences.edit();

                        APIService service= RetrofitURL1.getClient().create(APIService.class);
                        Call<ResultLoginResponse> call1=service.DoctorLogIn(
                                username,
                                password
                        );

                        call1.enqueue(new Callback<ResultLoginResponse>() {
                            @Override
                            public void onResponse(Call<ResultLoginResponse> call, Response<ResultLoginResponse> response) {

                                if (response.code() == 200 || response.code() == 201) {
                                    if (response.body().getSuccess().equals(true)) {
                                        progressDialog.dismiss();
                                        String DoctorID=response.body().getDoctorID();
                                        Log.e("LogIn ID","DoctorID : "+ DoctorID);
                                        editor1.putString("DoctorID",DoctorID);
                                        editor1.commit();
                                        Intent intent=new Intent(getApplicationContext(),Doctor_DashBoard_Activity.class);
                                        intent.putExtra("username",username);
                                        intent.putExtra("DoctorID", DoctorID);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else if (response.body().getSuccess().equals(false)){

                                        final SharedPreferences.Editor editor2=sharedPreferences.edit();

                                        APIService service= RetrofitURL1.getClient().create(APIService.class);
                                        Call<ResultLoginResponse> call1=service.AttendantLogIn(
                                                username,
                                                password
                                        );

                                        call1.enqueue(new Callback<ResultLoginResponse>() {
                                            @Override
                                            public void onResponse(Call<ResultLoginResponse> call, Response<ResultLoginResponse> response) {

                                                if (response.code() == 200 || response.code() == 201) {
                                                    if (response.body().getSuccess()) {
                                                        progressDialog.dismiss();
                                                        String AttendantID=response.body().getAttendantID();
                                                        Log.e("LogIn ID","AttendantID : "+ AttendantID);
                                                        editor2.putString("AttendantID",AttendantID);
                                                        editor2.commit();
                                                        Intent intent=new Intent(getApplicationContext(),AttendentDashBoardActivity.class);
                                                        intent.putExtra("username",username);
                                                        intent.putExtra("AttendantID", AttendantID);
                                                        startActivity(intent);
                                                        finish();

                                                    } else {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<ResultLoginResponse> call, Throwable t) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<ResultLoginResponse> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }

            }

            @Override
            public void onFailure(Call<ResultLoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    void checkLoggedIn() {
        @SuppressWarnings("WrongConstant")
        SharedPreferences preferences = getSharedPreferences("AllDataUser", MODE_APPEND);


        if (preferences.getString("PatientID","")!=""){
            startActivity(new Intent(getApplicationContext(),Dashboard_Activity.class));
            finish();
        }
        else if (preferences.getString("DoctorID","")!=""){
            startActivity(new Intent(getApplicationContext(),Doctor_DashBoard_Activity.class));
            finish();
        }
        else if (preferences.getString("AttendantID","")!=""){
            startActivity(new Intent(getApplicationContext(),AttendentDashBoardActivity.class));
            finish();
        }
        else {

        }

    }
}
