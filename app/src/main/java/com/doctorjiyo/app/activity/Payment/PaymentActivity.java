package com.doctorjiyo.app.activity.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.SlotComfirmBookingResult;
import com.doctorjiyo.app.API.Model.TokenBookingResult;
import com.doctorjiyo.app.API.Model.TokenComfirmBookingResult;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.MainActivity;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.BookAppointment.BookAppointmentActivity;
import com.doctorjiyo.app.activity.dashboard.Dashboard_Activity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = PaymentActivity.class.getSimpleName();

    ImageButton cancel;
    TextView DoctorName,DispensaryName,BookingDate,Token,Fees,PatientName,ContactNumber,Email,PurposeOfVisit,apttype;
    CircleImageView ImgPath;
    Intent intent;
    String sDispensaryID,sDoctorID,sPatientID,sBookingDate,sAptType;

    RelativeLayout cashinclinic,razorpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        intent=getIntent();
        sBookingDate=intent.getStringExtra("BookingDate");
        sDispensaryID=intent.getStringExtra("DispensaryID");
        sDoctorID=intent.getStringExtra("DoctorID");
        sPatientID=intent.getStringExtra("PatientID");
        sAptType=intent.getStringExtra("AptType");

        if (sAptType.equals("Token")){
            setAllTokenvalues();
        }else if (sAptType.equals("Slot")){
            setAllSlotvalues();
        }


        initComponent();

        BookingDate.setText(sBookingDate);

        //setAllvalues();



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
        PurposeOfVisit=findViewById(R.id.PurposeOfVisit);
        apttype=findViewById(R.id.apttype);

        cashinclinic=findViewById(R.id.cashinclinic);
        razorpay=findViewById(R.id.razorpay);

    }

    private void setAllTokenvalues() {

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<TokenComfirmBookingResult> call=service.getTokenConfirm(
                sDispensaryID,sDoctorID,sPatientID,sBookingDate
        );

        call.enqueue(new Callback<TokenComfirmBookingResult>() {
            @Override
            public void onResponse(Call<TokenComfirmBookingResult> call, final Response<TokenComfirmBookingResult> response) {
                if (response.code()==201||response.code()==200){
                    DoctorName.setText(response.body().getDoctorName());
                    DispensaryName.setText(response.body().getDispensaryName());
                    PurposeOfVisit.setText(response.body().getPurposeOfVisit());
                    Token.setText(response.body().getTokenNo());
                    apttype.setText("Token");
                    Fees.setText(response.body().getBookingFees());
                    PatientName.setText(response.body().getPatientName());
                    ContactNumber.setText(response.body().getContactNumber());
                    Email.setText(response.body().getEmail());

                    if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(ImgPath);
                    }else {
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).into(ImgPath);

                    }

                    final String razorpayKeyID=response.body().getMerchantKey();
                    final String razorpayAmount=response.body().getBookingFees();
                    razorpay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            razorpaypayment(razorpayKeyID,razorpayAmount,response.body().getMerchantLogo(),response.body().getMerchantName());
                        }
                    });

                    cashinclinic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            showCustomDialog("Paid in Clinic.");
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<TokenComfirmBookingResult> call, Throwable t) {

            }
        });

    }
    private void setAllSlotvalues() {

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<SlotComfirmBookingResult> call=service.getSlotConfirm(
                sDispensaryID,sDoctorID,sPatientID,sBookingDate
        );

        call.enqueue(new Callback<SlotComfirmBookingResult>() {
            @Override
            public void onResponse(Call<SlotComfirmBookingResult> call, final Response<SlotComfirmBookingResult> response) {
                if (response.code()==201||response.code()==200){
                    DoctorName.setText(response.body().getDoctorName());
                    DispensaryName.setText(response.body().getDispensaryName());
                    PurposeOfVisit.setText(response.body().getPurposeOfVisit());
                    Token.setText(response.body().getSlotTime());
                    apttype.setText("Slot Time");
                    Fees.setText(response.body().getBookingFees());
                    PatientName.setText(response.body().getPatientName());
                    ContactNumber.setText(response.body().getContactNumber());
                    Email.setText(response.body().getEmail());

                    if (response.body().getImgPath()==null||response.body().getImgPath()==""||response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")){
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(ImgPath);
                    }else {
                        Glide.with(getApplicationContext()).load(response.body().getImgPath()).into(ImgPath);

                    }

                    final String razorpayKeyID=response.body().getMerchantKey();
                    final String razorpayAmount=response.body().getBookingFees();
                    razorpay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            razorpaypayment(razorpayKeyID,razorpayAmount,response.body().getMerchantLogo(),response.body().getMerchantName());
                        }
                    });

                    cashinclinic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            showCustomDialog("Paid in Clinic.");
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<SlotComfirmBookingResult> call, Throwable t) {

            }
        });

    }

    void razorpaypayment(String razorpaykeyid,String Amount,String MerchentLogo,String MerchentName){
        Checkout checkout=new Checkout();
        checkout.setKeyID(razorpaykeyid);

        //checkout.setImage(URI.create(MerchentLogo));

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", MerchentName);
            options.put("description", "Appointment Payment");
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            double total = Double.parseDouble(Amount) * 100;

            options.put("amount", total);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {

        Log.e("onPaymentSuccess: ", s);
        showCustomDialog("Payment Paid Successfully");
        //confirmOrderPlace(true);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Log.e("onPaymentSuccess: ", s);
        showCustomDialog("Payment Pain Unsuccessful");
    }

    private void confirmOrderPlace(Boolean isPaid) {

        Toast.makeText(getApplicationContext(),"Payment Successfull",Toast.LENGTH_LONG).show();

    }

    private void showCustomDialog(String payment) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_payment);
        dialog.setCancelable(false);




        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.title)).setText(payment);

        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Intent intent=new Intent(getApplicationContext(), Dashboard_Activity.class);
                intent.putExtra("Fragment",true);
                startActivity(intent);
                finish();
                //Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}