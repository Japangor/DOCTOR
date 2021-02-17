package com.doctorjiyo.app.activity.sign_in;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.doctorjiyo.app.activity.WebView.WebViewPolicyActivity;
import com.doctorjiyo.app.activity.WebView.WebViewTermsActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.dashboard.Dashboard_Activity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button btn_signup,btn_signup_otp,btn_signup_google,btn_signup_facebook;
    TextInputEditText et_patientname,et_mobile,et_email,et_password;
    LinearLayout sign_in_here;

    TextView TermsConditions,PrivacyPolicy;
    CheckBox checkBox;

    ProgressDialog progressDialog;

    //google sign in
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initComponents();
        clickListeners();
        google_signin();

    }


    private void initComponents() {

        btn_signup=findViewById(R.id.signup);
        btn_signup_otp=findViewById(R.id.signup_with_mob);
        btn_signup_google=findViewById(R.id.signup_google);
        btn_signup_facebook=findViewById(R.id.signup_facebook);

        et_patientname=findViewById(R.id.patientname);
        et_mobile=findViewById(R.id.mobile);
        et_email=findViewById(R.id.email);
        et_password=findViewById(R.id.password);

        sign_in_here=findViewById(R.id.sign_in_for_account);

        TermsConditions=findViewById(R.id.termcond);
        TermsConditions.setClickable(true);
        PrivacyPolicy=findViewById(R.id.privacypolicy);
        checkBox=findViewById(R.id.checkbox);
    }

    private void clickListeners() {

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(),"Check Terms & Condition and Privacy & Policy.",Toast.LENGTH_LONG).show();
                }else {
                    userSignUp();
                }

            }
        });


        TermsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), WebViewTermsActivity.class);
                startActivity(intent);
            }
        });
        PrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), WebViewPolicyActivity.class);
                startActivity(intent);
            }
        });

        btn_signup_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInWithMobileEnterMobile_Activity.class));
            }
        });

        sign_in_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignIn_Activity.class));
                finish();
            }
        });

        btn_signup_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }

    //google sign in
    public  void google_signin(){

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //   callbackManager.onActivityResult(requestCode, resultCode, data);

        //google
        if (requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            Toast.makeText(getApplicationContext(),"signin success",Toast.LENGTH_LONG).show();
            /*
            Intent intent=new Intent(getApplicationContext(), Dashboard_Activity.class);
            startActivity(intent);
             */
        }else {
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }

    }




    private void userSignUp(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

/*
        Map<String,String> params= new HashMap<>();
        params.put("PatientName",et_patientname.getText().toString().trim());
        params.put("ContactNumber",et_mobile.getText().toString().trim());
        params.put("Email",et_email.getText().toString().trim());
        params.put("Password",et_password.getText().toString().trim());
*/
        RequestBody patientname = RequestBody.create(MediaType.parse("text/plain"), et_patientname.getText().toString().trim());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"),et_mobile.getText().toString().trim());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), et_email.getText().toString().trim());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), et_password.getText().toString().trim());

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call=service.SignUp(
                patientname,mobile,email,password
        );
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();
                try {
                    if (response.code()==200||response.code()==201){
                        if (response.body().getSuccess()){
                            //finish();
                            //SharedPrefManager.getInstance(getApplicationContext()).userLogin(username);
                            startActivity(new Intent(getApplicationContext(), SignIn_Activity.class));
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                            et_patientname.setText(null);
                            et_email.setText(null);
                            et_mobile.setText(null);
                            et_password.setText(null);
                        }else {
                            Toast.makeText(getApplicationContext(),"error : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"server error please try again later!",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"error : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
