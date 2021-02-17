package com.doctorjiyo.app.activity.Medical_Record;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.doctorjiyo.app.API.Model.RecordCatModel;
import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.utils.ViewAnimation;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload_Medical_Records_Activity extends AppCompatActivity {

    /*
    private static final String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] PERMISSION_CAPTURE={Manifest.permission.CAMERA};
    */

    LinearLayout add_document,attach;
    TextInputEditText doc_path, doc_name, doc_description;
    ImageView clear_doc;
    ImageButton cancelupload;
    Spinner doc_category;
    AppCompatButton upload;
    TextInputEditText createat;


    Integer REQUEST_CAMERA=100, SELECT_FILE=10,REQUSET_GALLERY = 0;
    private Bitmap bmp;
    String imagePATH;
    Intent intent;
    String sRecordID,sRecordName,sNotes,sRecordCatName,sPatientID,sPrescripttionID;

    String currentDateTimeString;

    private static final int MY_CAMERA_REQUEST_CODE = 100,REQUEST_EXTERNAL__STORAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__medical__records);


       // verifyStoragePermission(Upload_Medical_Records_Activity.this);
        //verifyCameraPermission(Upload_Medical_Records_Activity.this);

        intent=getIntent();
        sRecordID=intent.getStringExtra("RecordID");
        sRecordName=intent.getStringExtra("RecordName");
        sNotes=intent.getStringExtra("Notes");
        sRecordCatName=intent.getStringExtra("RecordCatName");
        sPatientID=intent.getStringExtra("userid");
        sPrescripttionID=intent.getStringExtra("PrescriptionID");


       // Toast.makeText(getApplicationContext(),sRecordID,Toast.LENGTH_LONG).show();


        ImageView imageView=findViewById(R.id.imgview);

        initComponents();
        setDocType();

        add_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        createat=findViewById(R.id.createdate);
        createat.setText(currentDateTimeString);


        clear_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc_path.setText(null);
                ViewAnimation.fadeOut(attach);
                attach.setVisibility(View.GONE);
            }
        });
        cancelupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            upload();
            }
        });

    }

    public  void initComponents(){

        add_document=findViewById(R.id.add_document);
        doc_path=findViewById(R.id.doc_path);
        doc_name=findViewById(R.id.doc_name);
        doc_category=findViewById(R.id.category);
        doc_description =findViewById(R.id.description);
        attach=findViewById(R.id.attach);
        clear_doc=findViewById(R.id.clear_doc);
        cancelupload=findViewById(R.id.cancel_record);
        upload=findViewById(R.id.upload_record);

    }

    private void SelectImage(){

        final CharSequence[] items={"Camera","Gallery","File", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Upload_Medical_Records_Activity.this);
        builder.setTitle("Add Record");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUSET_GALLERY);

                }else if (items[i].equals("File")){
                    Intent intent = new Intent();
                    intent.setType("*/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    Intent result=Intent.createChooser(intent,getText(R.string.choose));
                    startActivityForResult(result, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();


    }

    private void setDocType() {
        APIService service = RetrofitURL1.getClient().create(APIService.class);
        Call<ArrayList<RecordCatModel>> call = service.getRecordCat();

        call.enqueue(new Callback<ArrayList<RecordCatModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecordCatModel>> call, Response<ArrayList<RecordCatModel>> response) {
                ArrayList<RecordCatModel> RecordCatList = response.body();

                String[] recordCat = new String[RecordCatList.size()];

                for (int i = 0; i < RecordCatList.size(); i++){
                    recordCat[i] = RecordCatList.get(i).getRecordCatName();
                }
                ArrayAdapter aa = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,recordCat);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doc_category.setAdapter(aa);

                if (sRecordCatName != null){
                    for (int i = 0; i < RecordCatList.size(); i++){
                        String city11 = recordCat[i];
                        if (sRecordCatName.equals(city11)){
                            doc_category.setSelection(i);
                        }
                    }
                    doc_category.setEnabled(false);
                }else{
                    doc_category.setEnabled(true);
                }



            }
            @Override
            public void onFailure(Call<ArrayList<RecordCatModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if(resultCode== Activity.RESULT_OK){

                if(requestCode==REQUEST_CAMERA){
                    Bundle bundle = data.getExtras();
                    bmp = (Bitmap) bundle.get("data");
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    doc_path.setText(extStorageDirectory+"/temp.jpg");
                    attach.setVisibility(View.VISIBLE);
                }
                else if(requestCode==SELECT_FILE){
                    Uri selectedImageUri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePATH=cursor.getString(columnIndex);
                    //imagePATH=selectedImageUri.getPath().toString();
                    Log.e("FILE NAME : ",imagePATH);
                    Toast.makeText(getApplicationContext(),imagePATH,Toast.LENGTH_LONG).show();
                    doc_path.setText(imagePATH);
                    attach.setVisibility(View.VISIBLE);
                }
                else if (requestCode == REQUSET_GALLERY){
                    Uri selectedImageUri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePATH = cursor.getString(columnIndex);
                    doc_path.setText(imagePATH);
                    attach.setVisibility(View.VISIBLE);

                }

            }
            else {
                Toast.makeText(this, "You haven't picked Image/File", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }

    private void upload(){


        String record_cat=doc_category.getSelectedItem().toString();
        String recordname=doc_name.getText().toString();
        String notes=doc_description.getText().toString();
        String createdat=createat.getText().toString();
        String prescriptionId=sPrescripttionID;
        String patientId=sPatientID;

        final ProgressDialog progress = new ProgressDialog(Upload_Medical_Records_Activity.this);
        progress.setMessage("Please Wait");
        progress.setCancelable(false);
        progress.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        File file = null;

        if (imagePATH!=null) {
            file = new File(imagePATH);
        }else if(imagePATH==null){
            file = savebitmap(bmp);
        }else{
            Toast.makeText(getApplicationContext(),"You haven't picked file ",Toast.LENGTH_LONG).show();
        }

        assert file != null;


        Call<Result> call;

        if (sRecordID==null){
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
            RequestBody RecordName = RequestBody.create(MediaType.parse("text/plain"), recordname);
            RequestBody Notes= RequestBody.create(MediaType.parse("text/plain"), notes);
            RequestBody CreatedAt = RequestBody.create(MediaType.parse("text/plain"), createdat);
            RequestBody PatientID = RequestBody.create(MediaType.parse("text/plain"), patientId);
            RequestBody RecordCatName = RequestBody.create(MediaType.parse("text/plain"), record_cat);
            RequestBody RecordPrescriptionID = RequestBody.create(MediaType.parse("text/plain"), prescriptionId);

            APIService service=RetrofitURL1.getClient().create(APIService.class);
            call=service.uploadRecord(RecordName,Notes,CreatedAt,fileToUpload1,PatientID,RecordPrescriptionID,RecordCatName);
        }else{
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
            RequestBody recordid=RequestBody.create(MediaType.parse("text/plain"),sRecordID);
            RequestBody Notes= RequestBody.create(MediaType.parse("text/plain"), notes);
            RequestBody PatientID = RequestBody.create(MediaType.parse("text/plain"), sPatientID);
            RequestBody CreatedAt = RequestBody.create(MediaType.parse("text/plain"), createdat);
            RequestBody RecordName = RequestBody.create(MediaType.parse("text/plain"), recordname);

            APIService service=RetrofitURL1.getClient().create(APIService.class);
            call=service.editRecord(fileToUpload1,PatientID,recordid,Notes,CreatedAt,RecordName);

        }

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progress.dismiss();
                Log.d("Response", String.valueOf(response.body().getSuccess())+response.code());
                if (response.code() == 200 || response.code() == 201) {
                    if (response.body().getSuccess()) {
                          //  Toast.makeText(getApplicationContext(), "upload", Toast.LENGTH_SHORT).show();
                        if (sRecordID == null) {
                            Toast.makeText(getApplicationContext(), "upload", Toast.LENGTH_SHORT).show();
                            finish();
                            //startActivity(new Intent(Upload_Medical_Records_Activity.this, Single_ProductActivity.class).putExtra("username", username));
                        } else {
                            Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Upload_Medical_Records_Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(EditDocumentsActivity.this, MyDocumentsActivity.class).putExtra("username", String.valueOf(username)));
                            finish();
                            Log.d("EditDoc", response.body().getMessage());
                        }
                    } else {
                        Log.e("Upload Err1 : ",response.body().getMessage());
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Upload Err2 : ",response.body().getMessage());
                    Toast.makeText(getApplicationContext(), "Server error please try again later!", Toast.LENGTH_LONG).show();
                }

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progress.dismiss();
                Log.e("Upload Err3 : ",t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });


    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.jpg");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.jpg");

        }

        imagePATH=extStorageDirectory+"/temp.jpg";

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
