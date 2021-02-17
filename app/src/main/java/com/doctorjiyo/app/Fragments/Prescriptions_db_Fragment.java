package com.doctorjiyo.app.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;

import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.PrescriptionListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Prescriptions_db_Fragment extends Fragment {

    String patientID;

    ShimmerRecyclerView recycler_view_article;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_prescriptions_db, container, false);

        patientID=this.getArguments().getString("userid");
        Toast.makeText(getContext(),patientID, Toast.LENGTH_LONG).show();

        recycler_view_article=view.findViewById(R.id.recycler_view_prescription);
        loadPrescriptions();

        return view;
    }

    private void loadPrescriptions() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
       // progressDialog.show();

        try {

            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<PrescriptionModel>> call = service.getPrecriptionsList(
                    patientID
            );

            call.enqueue(new Callback<ArrayList<PrescriptionModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PrescriptionModel>> call, Response<ArrayList<PrescriptionModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<PrescriptionModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getContext());
                            recycler_view_article.setLayoutManager(layoutManager);

                            PrescriptionListAdapter recyclerViewAdapter = new PrescriptionListAdapter(docList,getContext(),patientID);

                            recycler_view_article.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PrescriptionModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
