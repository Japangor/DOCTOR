package com.doctorjiyo.app.activity.Prescription;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.doctorjiyo.app.API.Model.PrescriptionDetails;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.SingleMedicineAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptionActivity extends AppCompatActivity {

    TextView title;
    String PrescriptionID,PrescriptionTitle;
    ImageButton close;

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        Intent intent=getIntent();
        PrescriptionID=intent.getStringExtra("PrescriptionID");
        PrescriptionTitle=intent.getStringExtra("PrescriptionTitle");

        initComponents();

        title.setText(PrescriptionTitle);


        loadPrescriptionDetails();


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initComponents() {

        recyclerView=findViewById(R.id.recycler_view_single_prescription);
        close=findViewById(R.id.close);
        title=findViewById(R.id.title);


    }

    private void loadPrescriptionDetails() {




            APIService service = RetrofitURL1.getClient().create(APIService.class);
            Call<ArrayList<PrescriptionDetails>> call = service.getPrescriptionDetails(
                    PrescriptionID
            );

            call.enqueue(new Callback<ArrayList<PrescriptionDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<PrescriptionDetails>> call, Response<ArrayList<PrescriptionDetails>> response) {

                    if (response.code()==200 || response.code()==201) {
                        if (!response.equals(null)) {
                            ArrayList<PrescriptionDetails> docList = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);

                            SingleMedicineAdapter recyclerViewAdapter = new SingleMedicineAdapter(getApplicationContext(), docList);

                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"failed to loading",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PrescriptionDetails>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }

            });
    }

}
