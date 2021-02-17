package com.doctorjiyo.app.activity.BookAppointment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import com.doctorjiyo.app.API.Model.AppointmentTypeResult;
import com.doctorjiyo.app.API.Model.DispensaryListResult;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.doctorjiyo.app.adapter.SlotDispensaryAdapter;
import com.doctorjiyo.app.adapter.TokenDispensaryAdapter;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointmentactivity2 extends AppCompatActivity implements DatePickerListener {

    private HorizontalCalendar horizontalCalendar;

    ShimmerRecyclerView dispensaryRecyclerView;
    private LinearLayoutManager layoutManager;

    ImageButton imageButton;
    String DoctorID;

    ArrayList<DispensaryListResult> docList;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        imageButton=findViewById(R.id.close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dispensaryRecyclerView=findViewById(R.id.dispensary_recyclerView);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        loadDispensary(currentDate);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        // find the picker
        HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.datePicker);

        // initialize it and attach a listener
        picker
                .setListener(this)
                .setDays(36)
                .setOffset(3)
                .setDateSelectedColor(Color.DKGRAY)
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.DKGRAY)
                .setTodayButtonTextColor(getColor(R.color.colorPrimary))
                .setTodayDateTextColor(getColor(R.color.colorPrimary))
                .setTodayDateBackgroundColor(Color.GRAY)
                .setUnselectedDayTextColor(Color.DKGRAY)
                .setDayOfWeekTextColor(Color.DKGRAY)
                .setUnselectedDayTextColor(getColor(R.color.white))
                .showTodayButton(false)
                .init();
        picker.setBackgroundColor(Color.LTGRAY);
        picker.setDate(new DateTime());






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

        horizontalCalendar.getConfig()
                .setFormatBottomText("EEE");



        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Date date1=date.getTime();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                String finalDate=format.format(date1);
                loadDispensary(finalDate);
                Toast.makeText(getApplicationContext(),finalDate,Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

    }

    @Override
    public void onDateSelected(@NonNull final DateTime dateSelected) {
        // log it for demo
        Log.i("HorizontalPicker", "Selected date is " + dateSelected.toString());
        Date date1=dateSelected.toDate();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String finalDate=format.format(date1);
        loadDispensary(finalDate);
        //Toast.makeText(getApplicationContext(),"Selected date is " + finalDate,Toast.LENGTH_LONG).show();
    }

    void loadDispensary(final String bookikingdate){
        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<AppointmentTypeResult> call=service.getAppointmentType();

        call.enqueue(new Callback<AppointmentTypeResult>() {
            @Override
            public void onResponse(Call<AppointmentTypeResult> call, Response<AppointmentTypeResult> response) {
                if (response.code()==200||response.code()==201){
                    if (response.body().getSuccess()!=true){

                    }else {
                        String apppointmenttype=response.body().getAppointmentType();

                        if (apppointmenttype=="Token"||apppointmenttype.equals("Token")){

                            APIService service= RetrofitURL1.getClient().create(APIService.class);
                            Call<ArrayList<DispensaryListResult>> call1=service.getToken(
                                    RetrofitURL1.DoctorID,
                                    bookikingdate
                            );

                            call1.enqueue(new Callback<ArrayList<DispensaryListResult>>() {
                                @Override
                                public void onResponse(Call<ArrayList<DispensaryListResult>> call, Response<ArrayList<DispensaryListResult>> response) {

                                    docList = response.body();
                                    layoutManager = new LinearLayoutManager(getApplicationContext());
                                    dispensaryRecyclerView.setLayoutManager(layoutManager);


                                    TokenDispensaryAdapter recyclerViewAdapter = new TokenDispensaryAdapter(getApplicationContext(), docList,bookikingdate);

                                    dispensaryRecyclerView.setAdapter(recyclerViewAdapter);
                                    recyclerViewAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<ArrayList<DispensaryListResult>> call, Throwable t) {

                                }
                            });

                        }else if (apppointmenttype=="Slot"||apppointmenttype.equals("Slot")){
                            APIService service= RetrofitURL1.getClient().create(APIService.class);
                            Call<ArrayList<DispensaryListResult>> call1=service.getSlot(
                                    RetrofitURL1.DoctorID,
                                    bookikingdate
                            );

                            call1.enqueue(new Callback<ArrayList<DispensaryListResult>>() {
                                @Override
                                public void onResponse(Call<ArrayList<DispensaryListResult>> call, Response<ArrayList<DispensaryListResult>> response) {

                                    docList = response.body();
                                    layoutManager = new LinearLayoutManager(getApplicationContext());
                                    dispensaryRecyclerView.setLayoutManager(layoutManager);


                                    SlotDispensaryAdapter recyclerViewAdapter = new SlotDispensaryAdapter(getApplicationContext(), docList,bookikingdate);

                                    dispensaryRecyclerView.setAdapter(recyclerViewAdapter);
                                    recyclerViewAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<ArrayList<DispensaryListResult>> call, Throwable t) {

                                }
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<AppointmentTypeResult> call, Throwable t) {

            }
        });

    }

    /*
    private void loadDespensary() {
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
        // progressDialog.show();

        try {

            APIService service = RetrofitURL.getClient().create(APIService.class);
            Call<ArrayList<DispensaryModel>> call = service.getDispensaryList();

            call.enqueue(new Callback<ArrayList<DispensaryModel>>() {
                @Override
                public void onResponse(Call<ArrayList<DispensaryModel>> call, Response<ArrayList<DispensaryModel>> response) {
                    //progressDialog.dismiss();
                    if (response.code()==200) {
                        if (!response.equals(null)) {
                            ArrayList<DispensaryModel> docList = response.body();
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            dispensaryRecyclerView.setLayoutManager(layoutManager);

                            DispensaryAdapter recyclerViewAdapter = new DispensaryAdapter(getApplicationContext(), docList);

                            dispensaryRecyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<DispensaryModel>> call, Throwable t) {
                    //progressDialog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}
