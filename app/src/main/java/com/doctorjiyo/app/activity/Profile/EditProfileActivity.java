package com.doctorjiyo.app.activity.Profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.utils.FileUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Medical_Record.Upload_Medical_Records_Activity;
import com.doctorjiyo.app.utils.Tools;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    CircleImageView profilepic;
    Button bt_submit;
    FloatingActionButton getPic;
    ImageButton close;
    EditText et_name,et_location,et_contact,et_emergencycontact,et_email,et_gender,et_dob,et_maritalstatus,et_bldgrp,et_height,et_weight,et_smoking,et_alcohol,et_food;
    //requestcode
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = Upload_Medical_Records_Activity.class.getSimpleName();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    Integer REQUEST_CAMERA=1888, SELECT_FILE=10,REQUSET_GALLERY = 0;

    String imagePATH;
    Bitmap bmp;
    Uri mImageUri;
    Uri ResultUri;

    Intent intent;
    String patientid,patientname,patientcontactnumber,patientemail,patientgender,patientdob,patientbldgrp,patientms,patientheight,patientweight,patientemgContact,patientlocation,patientsmoking,patientalcohol,patientfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initComponent();

        intent=getIntent();
        patientid=intent.getStringExtra("PatientID");
        imagePATH=intent.getStringExtra("patientphoto");
        patientname=intent.getStringExtra("PatientName");
        patientcontactnumber=intent.getStringExtra("ContactNumber");
        patientemail=intent.getStringExtra("Email");
        patientgender=intent.getStringExtra("Gender");
        patientdob=intent.getStringExtra("DOB");
        patientbldgrp=intent.getStringExtra("BloodGroup");
        patientms=intent.getStringExtra("MaritalStatus");
        patientheight=intent.getStringExtra("Height");
        patientweight=intent.getStringExtra("Weight");
        patientemgContact=intent.getStringExtra("EmergencyContact");
        patientlocation=intent.getStringExtra("Location");
        patientsmoking=intent.getStringExtra("SmokingHabits");
        patientalcohol=intent.getStringExtra("AlcoholConsumption");
        patientfood=intent.getStringExtra("FoodPreferences");

        //Toast.makeText(getApplicationContext(),patientphoto,Toast.LENGTH_SHORT).show();
        if (imagePATH==null||imagePATH==""||imagePATH.equals(null)||imagePATH.equals("")){
            Picasso.with(getApplicationContext()).load(R.drawable.img_applogo).into(profilepic);
        }else {
            Picasso.with(getApplicationContext()).load(imagePATH).placeholder(R.drawable.img_applogo).into(profilepic);
        }

        et_name.setText(patientname);
        et_location.setText(patientlocation);
        et_emergencycontact.setText(patientemgContact);
        et_contact.setText(patientcontactnumber);
        et_email.setText(patientemail);
        et_gender.setText(patientgender);
        et_dob.setText(patientdob);
        et_maritalstatus.setText(patientms);
        et_bldgrp.setText(patientbldgrp);
        et_height.setText(patientheight);
        et_weight.setText(patientweight);
        et_smoking.setText(patientsmoking);
        et_alcohol.setText(patientalcohol);
        et_food.setText(patientfood);




        clickListeners();

    }

    private void initComponent() {

        close=findViewById(R.id.close);
        et_name=findViewById(R.id.name);
        et_location=findViewById(R.id.location);
        et_contact=findViewById(R.id.contactnumber);
        et_emergencycontact=findViewById(R.id.emergency_phone);
        et_email=findViewById(R.id.emailid);
        et_gender=findViewById(R.id.gender);
        et_dob=findViewById(R.id.birth_date);
        et_maritalstatus=findViewById(R.id.marital_status);
        et_bldgrp=findViewById(R.id.bloog_grp);
        et_height=findViewById(R.id.height);
        et_weight=findViewById(R.id.weight);
        et_smoking=findViewById(R.id.smokinghabbits);
        et_alcohol=findViewById(R.id.alcohol_cunsumption);
        et_food=findViewById(R.id.food);
        getPic=findViewById(R.id.get_profilepic);
        profilepic=findViewById(R.id.profile_pic);
        bt_submit=findViewById(R.id.bt_submit);

    }

    private  void clickListeners(){

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        et_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showGenderDialog(v);
            }
        });

        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDOBDialog(v);
            }
        });

        et_maritalstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMaritalStatusDialog(v);
            }
        });

        et_bldgrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBloodGroupDialog(v);
            }
        });

        et_smoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSmokingHabitsDialog(v);
            }
        });

        et_alcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlcoholConsumptionDialog(v);
            }
        });

        et_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodPreferenceDialog(v);
            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }

    private void selectImage(){
        CropImage.startPickImageActivity(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri rUri = result.getUri();
                profilepic.setImageURI(rUri);

                ResultUri=result.getUri();

                imagePATH=String.valueOf(result.getUri());
                Toast.makeText(getApplicationContext(),imagePATH,Toast.LENGTH_LONG).show();



            }
        }

    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(this);
    }

    private void showGenderDialog(final View v) {
        final String[] array = new String[]{
                "Male", "Female", "Other"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gender");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void showDOBDialog(final View v) {
        final Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        //Calendar calendar = Calendar.getInstance();
                        cur_calender.set(Calendar.YEAR, year);
                        cur_calender.set(Calendar.MONTH, monthOfYear);
                        cur_calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date = cur_calender.getTimeInMillis();
                        ((EditText) v).setText(Tools.getFormattedDateShort(date));
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.green_400));
        //datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Expiration Date");
    }
    private void showMaritalStatusDialog(final View v) {
        final String[] array = new String[]{
                "Single", "Married", "Divorced","Widowed"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Marital Status");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void showBloodGroupDialog(final View v) {
        final String[] array = new String[]{
                "A+", "A-", "B+","B-","AB+","AB-","O+","O-"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Blood Group");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void showSmokingHabitsDialog(final View v) {
        final String[] array = new String[]{
                "I Don't Smoke", "I Have Quit", "3-5/day","5-10/day","More Then 10/day"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Smoking Habits");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void showAlcoholConsumptionDialog(final View v) {
        final String[] array = new String[]{
                "Non Drinker", "Occasionally", "Social","Regular"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alcohol Consumption");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void showFoodPreferenceDialog(final View v) {
        final String[] array = new String[]{
                "Vegetarian", "Non Vegetarian"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Food Preference");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    private void upload(){

        final ProgressDialog progress = new ProgressDialog(EditProfileActivity.this);
        progress.setMessage("Please Wait");
        progress.setCancelable(false);
        progress.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);




        File file = null;//= new File(ResultUri.getPath());
        Bitmap bitmap;
        //          bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        bitmap = ((BitmapDrawable)profilepic.getDrawable()).getBitmap();
        file=savebitmap(bitmap);

        assert file != null;

        RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
        RequestBody PatientID = RequestBody.create(MediaType.parse("text/plain"), patientid);
        RequestBody PatientName= RequestBody.create(MediaType.parse("text/plain"), et_name.getText().toString());
        RequestBody ContactNumber = RequestBody.create(MediaType.parse("text/plain"), et_contact.getText().toString());
        RequestBody Email = RequestBody.create(MediaType.parse("text/plain"), et_email.getText().toString());
        RequestBody Gender = RequestBody.create(MediaType.parse("text/plain"), et_gender.getText().toString());
        RequestBody DOB = RequestBody.create(MediaType.parse("text/plain"), et_dob.getText().toString());
        RequestBody BloodGroup = RequestBody.create(MediaType.parse("text/plain"), et_bldgrp.getText().toString());
        RequestBody MaritalStatus = RequestBody.create(MediaType.parse("text/plain"), et_maritalstatus.getText().toString());
        RequestBody Height = RequestBody.create(MediaType.parse("text/plain"), et_height.getText().toString());
        RequestBody Weight = RequestBody.create(MediaType.parse("text/plain"), et_weight.getText().toString());
        RequestBody EmergencyContact = RequestBody.create(MediaType.parse("text/plain"), et_emergencycontact.getText().toString());
        RequestBody Location = RequestBody.create(MediaType.parse("text/plain"), et_location.getText().toString());
        RequestBody SmokingHabits = RequestBody.create(MediaType.parse("text/plain"), et_smoking.getText().toString());
        RequestBody AlcoholConsumption = RequestBody.create(MediaType.parse("text/plain"), et_alcohol.getText().toString());
        RequestBody ActivitiesLevel = RequestBody.create(MediaType.parse("text/plain"), "act");
        RequestBody FoodPreferences = RequestBody.create(MediaType.parse("text/plain"), et_food.getText().toString());
        APIService getResponse = RetrofitURL1.getClient().create(APIService.class);
        Call<Result> call;
        call = getResponse.editPatientProfile(
                fileToUpload1,
                PatientID,
                PatientName,
                ContactNumber,
                Email,
                Gender,
                DOB,
                BloodGroup,
                MaritalStatus,
                Height,
                Weight,
                EmergencyContact,
                Location,
                SmokingHabits,
                AlcoholConsumption,
                ActivitiesLevel,
                FoodPreferences
        );


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progress.dismiss();
                Log.d("Response", String.valueOf(response.body().getSuccess())+response.code());
                if (response.code() == 200 || response.code() == 201) {
                    if (response.body().getSuccess()) {

                        Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Server error please try again later!", Toast.LENGTH_LONG).show();
                }

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progress.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        String temp = null;
        File file = new File(extStorageDirectory, "temp.jpg");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.jpg");

        }else {
            file = new File(extStorageDirectory, "temp.jpg");
        }

        imagePATH=extStorageDirectory+"/temp.jpg";

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            Log.e("file",e.getMessage());
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

}
