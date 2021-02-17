package com.doctorjiyo.app.Attendent.Actiivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import com.doctorjiyo.app.API.Model.DispensaryTokenModel;
import com.doctorjiyo.app.API.RetrofitURL;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.Attendent.Adapter.AttendentDispensaryAdapter;
import com.doctorjiyo.app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendentAppointmentListActivity extends AppCompatActivity {

    private HorizontalCalendar horizontalCalendar;
    ImageButton imageButton;

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendent_appointment_list);

        imageButton=findViewById(R.id.close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.recycler_view);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);


        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)   // Number of Dates cells shown on screen (default to 5).
                .configure()    // starts configuration.
                // .formatTopText(String dateFormat)       // default to "MMM".
                .formatMiddleText("dd")    // default to "dd".
                .formatBottomText("EEE")    // default to "EEE".
                .showTopText(true)// show or hide TopText (default to true).
                .showBottomText(false)           // show or hide BottomText (default to true).
                // .textColor(int normalColor, int selectedColor)    // default to (Color.LTGRAY, Color.WHITE).
                //  .selectedDateBackground(getDrawable(R.drawable.gradient))      // set selected date cell background.
                .selectorColor(R.color.blue_grey_600)               // set selection indicator bar's color (default to colorAccent).
                .end()          // ends configuration.
                .defaultSelectedDate(Calendar.getInstance())    // Date to be selected at start (default to current day `Calendar.getInstance()`).
                .build();

        Calendar currentCal=Calendar.getInstance();
        final Date currentDate=currentCal.getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String cDate=dateFormat.format(currentDate);
        Toast.makeText(getApplicationContext(),cDate,Toast.LENGTH_SHORT).show();
        loadDespensary();

        horizontalCalendar.getConfig().setFormatBottomText("EEE");

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Date date1=date.getTime();
                if (date1.equals(currentDate)){
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    String cDate=dateFormat.format(currentDate);
                    Toast.makeText(getApplicationContext(),cDate,Toast.LENGTH_SHORT).show();
                }
                /*else
                {
                    date1=date.getTime();
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                    String finalDate=format.format(date1);
                    Toast.makeText(getApplicationContext(),finalDate,Toast.LENGTH_SHORT).show();
                }
                loadDespensary();

                 */
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

    }

    private void loadDespensary() {
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
        // progressDialog.show();

        /*
        try {
         APIService service = RetrofitURL.getClient().create(APIService.class);
            Call<ArrayList<DispensaryTokenModel>> call = service.getDispensaryTokenList();

            call.enqueue(new Callback<ArrayList<DispensaryTokenModel>>() {
                @Override
                public void onResponse(Call<ArrayList<DispensaryTokenModel>> call, Response<ArrayList<DispensaryTokenModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<DispensaryTokenModel> docList = response.body();
                            linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayoutManager);

                            AttendentDispensaryAdapter recyclerViewAdapter = new AttendentDispensaryAdapter(getApplicationContext(), docList);

                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<DispensaryTokenModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

 */
    }
}
