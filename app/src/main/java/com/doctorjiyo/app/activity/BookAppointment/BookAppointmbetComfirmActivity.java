
package com.doctorjiyo.app.activity.BookAppointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.Model.SlotBookingResult;
import com.doctorjiyo.app.API.Model.TokenBookingResult;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Payment.PaymentActivity;
import com.doctorjiyo.app.activity.sign_in.SignIn_Activity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAppointmbetComfirmActivity extends AppCompatActivity {

    ImageButton cancel;
    TextView DoctorName,DispensaryName,BookingDate,Token,Fees,PatientName,ContactNumber,Email,apttype,tokenCap;
    EditText etPurposeOfVisit;
    Button btn_bookAppointment;
    CircleImageView ImgPath;

    Intent intent;

    String sDispensaryID,sDoctorID,sPatientID,sBookingDate,sToken,sDispensaryTiming,sSlotTime,sAptType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointmbet_comfirm);

        intent=getIntent();
        sDispensaryID=intent.getStringExtra("DispensaryID");
        sBookingDate=intent.getStringExtra("BookingDate");
        sToken=intent.getStringExtra("Token");
        sDispensaryTiming=intent.getStringExtra("DispensaryTiming");
        sSlotTime=intent.getStringExtra("SlotTime");
        sAptType=intent.getStringExtra("AptType");

        Log.e("sDispensaryID",sDispensaryID);
        Log.e("sAptType",sAptType);

        sDoctorID= RetrofitURL1.DoctorID;

        @SuppressWarnings("WrongConstant")
        SharedPreferences sharedPreferences=getSharedPreferences("AllDataUser",MODE_APPEND);
        sPatientID=sharedPreferences.getString("PatientID","1");

        initComponent();


        if (sAptType.equals("Token")){
            setAllTokenvalues();
        }else if (sAptType.equals("Slot")){
            setAllSlotvalues();
        }
        if (sAptType.equals("Token")){
            apttype.setText("Token");
            Token.setText(sToken);
            tokenCap.setVisibility(View.VISIBLE);
        }else if (sAptType.equals("Slot")){
            apttype.setText("Slot Time");
            Token.setText(sSlotTime);
            tokenCap.setVisibility(View.GONE);
        }

        BookingDate.setText(sBookingDate);

        btn_bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sAptType.equals("Token")){
                    submitTokenAppointment();
                }else {
                    submitSlotAppointment();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initComponent() {

        cancel=findViewById(R.id.close);
        DoctorName=findViewById(R.id.DoctorName);
        DispensaryName=findViewById(R.id.DispensaryName);
        BookingDate=findViewById(R.id.BookingDate);
        Token=findViewById(R.id.Token);
        Fees=findViewById(R.id.Fees);
        PatientName=findViewById(R.id.PatientName);
        ContactNumber=findViewById(R.id.ContactNumber);
        Email=findViewById(R.id.Email);
        ImgPath=findViewById(R.id.ImgPath);
        etPurposeOfVisit=findViewById(R.id.PurposeOfVisit);
        btn_bookAppointment=findViewById(R.id.btn_bookAppointment);
        apttype=findViewById(R.id.apttype);
        tokenCap=findViewById(R.id.tokenCap);
    }


    private void setAllTokenvalues() {

        APIService service=RetrofitURL1.getClient().create(APIService.class);
        Call<TokenBookingResult> call=service.getTokenBooking(
                sDispensaryID,sDoctorID,sPatientID
        );

        call.enqueue(new Callback<TokenBookingResult>() {
            @Override
            public void onResponse(Call<TokenBookingResult> call, Response<TokenBookingResult> response) {
                if (response.code()==201||response.code()==200){
                    DoctorName.setText(response.body().getDoctorName());
                    DispensaryName.setText(response.body().getDispensaryName());
                    Fees.setText(response.body().getBookingFees());
                    PatientName.setText(response.body().getPatientName());
                    ContactNumber.setText(response.body().getContactNumber());
                    Email.setText(response.body().getEmail());

                    if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(ImgPath);
                    }else {
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).into(ImgPath);

                    }

                }
            }

            @Override
            public void onFailure(Call<TokenBookingResult> call, Throwable t) {

            }
        });

    }

    private void setAllSlotvalues() {

        APIService service=RetrofitURL1.getClient().create(APIService.class);
        Call<SlotBookingResult> call=service.getSlotBooking(
                sDispensaryID,sDoctorID,sPatientID
        );

        call.enqueue(new Callback<SlotBookingResult>() {
            @Override
            public void onResponse(Call<SlotBookingResult> call, Response<SlotBookingResult> response) {
                if (response.code()==201||response.code()==200){
                    DoctorName.setText(response.body().getDoctorName());
                    DispensaryName.setText(response.body().getDispensaryName());
                    Fees.setText(response.body().getBookingFees());
                    PatientName.setText(response.body().getPatientName());
                    ContactNumber.setText(response.body().getContactNumber());
                    Email.setText(response.body().getEmail());
                    if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(ImgPath);
                    }else {
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).into(ImgPath);

                    }
                }
            }
            @Override
            public void onFailure(Call<SlotBookingResult> call, Throwable t) {
            }
        });
    }
    private void submitTokenAppointment(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Map<String,String> param=new HashMap<>();
        param.put("PatientID",sPatientID);
        param.put("DoctorID",sPatientID);
        param.put("DispensaryID",sDispensaryID);
        param.put("PurposeOfVisit",etPurposeOfVisit.getText().toString());
        param.put("BookingDate",sBookingDate);
        param.put("DispensaryTiming",sDispensaryTiming);
        //param.put("TokenNo",sToken);
        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call=service.submitTokenAppointment(
                param
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

                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                            Log.e("Booked",response.body().getMessage());

                            Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
                            intent.putExtra("DispensaryID",sDispensaryID);
                            intent.putExtra("BookingDate",sBookingDate);
                            intent.putExtra("DoctorID",sDoctorID);
                            intent.putExtra("PatientID",sPatientID);
                            intent.putExtra("AptType",sAptType);
                            startActivity(intent);
                            finish();

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

    private void submitSlotAppointment(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Map<String,String> param=new HashMap<>();
        param.put("PatientID",sPatientID);
        param.put("DoctorID",sPatientID);
        param.put("DispensaryID",sDispensaryID);
        param.put("PurposeOfVisit",etPurposeOfVisit.getText().toString());
        param.put("BookingDate",sBookingDate);
        param.put("DispensaryTiming",sDispensaryTiming);
        param.put("SlotTime",sSlotTime);

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call=service.submitSlotAppointment(
                param
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

                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                            Log.e("Booked",response.body().getMessage());

                            Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
                            intent.putExtra("DispensaryID",sDispensaryID);
                            intent.putExtra("BookingDate",sBookingDate);
                            intent.putExtra("DoctorID",sDoctorID);
                            intent.putExtra("PatientID",sPatientID);
                            intent.putExtra("AptType",sAptType);
                            startActivity(intent);
                            finish();

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


}
