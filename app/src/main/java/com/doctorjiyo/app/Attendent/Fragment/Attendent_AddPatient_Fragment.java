package com.doctorjiyo.app.Attendent.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorjiyo.app.API.Model.Doc_PatientListModel;
import com.doctorjiyo.app.API.Model.DoctorList;
import com.doctorjiyo.app.API.Model.RecordCatModel;
import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Attendent.Actiivty.Attendent_CreateNewPatient_Activity;
import com.doctorjiyo.app.Doctor.Adapter.PatientChatListAdpater;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.activity.Profile.EditProfileActivity;
import com.doctorjiyo.app.utils.Tools;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Attendent_AddPatient_Fragment extends Fragment {

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    EditText search_bar;

    FloatingActionButton addpatient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attendent__add_patient, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        addpatient=view.findViewById(R.id.addpatient);
        addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Attendent_CreateNewPatient_Activity.class));
            }
        });

        final String AttendentID="1";

        loadPatientList();

        search_bar=view.findViewById(R.id.search_bar);
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Toast.makeText(getContext(), s.toString(),Toast.LENGTH_LONG).show();
                loadPatientList(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }


    private void loadPatientList(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ArrayList<Doc_PatientListModel>> call=service.getPatientList(
                RetrofitURL1.DoctorID
        );

        call.enqueue(new Callback<ArrayList<Doc_PatientListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<Doc_PatientListModel>> call, Response<ArrayList<Doc_PatientListModel>> response) {
                if (response.code()==200 ){
                    if (!response.equals(null)){
                        ArrayList<Doc_PatientListModel> patientLists=response.body();
                        layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        PatientChatListAdpater patientChatListAdpater=new PatientChatListAdpater(getContext(),patientLists,"1");
                        recyclerView.setAdapter(patientChatListAdpater);
                        patientChatListAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Doc_PatientListModel>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadPatientList(String searchterm){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ArrayList<Doc_PatientListModel>> call=service.getSearchPatientList(
                RetrofitURL1.DoctorID,
                searchterm
        );

        call.enqueue(new Callback<ArrayList<Doc_PatientListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<Doc_PatientListModel>> call, Response<ArrayList<Doc_PatientListModel>> response) {
                if (response.code()==200 ){
                    if (!response.equals(null)){
                        ArrayList<Doc_PatientListModel> patientLists=response.body();
                        layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        PatientChatListAdpater patientChatListAdpater=new PatientChatListAdpater(getContext(),patientLists,"1");
                        recyclerView.setAdapter(patientChatListAdpater);
                        patientChatListAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Doc_PatientListModel>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}