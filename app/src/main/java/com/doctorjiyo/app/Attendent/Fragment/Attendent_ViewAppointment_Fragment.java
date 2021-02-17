package com.doctorjiyo.app.Attendent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doctorjiyo.app.Attendent.Actiivty.AttendentAppointmentListActivity;
import com.doctorjiyo.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class Attendent_ViewAppointment_Fragment extends Fragment {

    private HorizontalCalendar horizontalCalendar;
    FloatingActionButton addAppointment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attendent__view_appointment, container, false);


        addAppointment=view.findViewById(R.id.addAppointment);
        addAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AttendentAppointmentListActivity.class));
            }
        });

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);


        horizontalCalendar = new HorizontalCalendar.Builder(view,R.id.calendarView)
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


                Toast.makeText(getActivity(),finalDate,Toast.LENGTH_SHORT).show();
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

        return view;
    }
}