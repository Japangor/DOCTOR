package com.doctorjiyo.app.Doctor.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.doctorjiyo.app.API.Model.Doc_PatientListModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Doctor.API.DoctorService_;
import com.doctorjiyo.app.Doctor.API.Model.PatientList;
import com.doctorjiyo.app.Doctor.API.RetrofiltURL_Doc;
import com.doctorjiyo.app.Doctor.Adapter.PatientChatListAdpater;
import com.doctorjiyo.app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doc_Patients_Fragment extends Fragment {

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    EditText search_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doc__patients, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

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
                        PatientChatListAdpater patientChatListAdpater=new PatientChatListAdpater(getContext(),patientLists,"0");
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
                        PatientChatListAdpater patientChatListAdpater=new PatientChatListAdpater(getContext(),patientLists,"0");
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