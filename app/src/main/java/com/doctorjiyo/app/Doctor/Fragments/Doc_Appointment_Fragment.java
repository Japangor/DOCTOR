package com.doctorjiyo.app.Doctor.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import com.doctorjiyo.app.Doctor.API.DoctorService;
import com.doctorjiyo.app.Doctor.API.Model.AppointmentList;
import com.doctorjiyo.app.Doctor.API.RetrofitURL_Doctor;
import com.doctorjiyo.app.Doctor.Adapter.PatientListAppointmentAdpater;
import com.doctorjiyo.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Doc_Appointment_Fragment extends Fragment {


    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
   HorizontalCalendar horizontalCalendar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doc__appointment, container, false);


        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH,-1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);



        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
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

                date=Calendar.getInstance();
                date.add(Calendar.DATE,1);
                Date date1=date.getTime();
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                String finalDate=format.format(date1);


                Toast.makeText(getContext(),finalDate,Toast.LENGTH_SHORT).show();

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



        recyclerView=view.findViewById(R.id.patientlist);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        loadPatientList();

        return view;
    }

    private void loadPatientList(){

        DoctorService service= RetrofitURL_Doctor.getClient().create(DoctorService.class);
        Call<ArrayList<AppointmentList>> call=service.getAppointmentList();

        call.enqueue(new Callback<ArrayList<AppointmentList>>() {
            @Override
            public void onResponse(Call<ArrayList<AppointmentList>> call, Response<ArrayList<AppointmentList>> response) {
                if (response.code()==200 ){
                    if (!response.equals(null)){
                        ArrayList<AppointmentList> patientLists=response.body();
                        layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        PatientListAppointmentAdpater patientListAppointmentAdpater=new PatientListAppointmentAdpater(getContext(),patientLists);
                        recyclerView.setAdapter(patientListAppointmentAdpater);
                        patientListAppointmentAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AppointmentList>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
