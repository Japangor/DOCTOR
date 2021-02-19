package com.doctorjiyo.app.activity.BookAppointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doctorjiyo.app.R;

import java.util.List;

/**
 * Created by oshin on 1/30/2018.
 */

class CustomAdapter extends BaseAdapter{

    private Context applicationContext;
    private int sample;
    private List<doctormodel> jsonModels;


    CustomAdapter(Context applicationContext, int sample, List<doctormodel> jsonModels) {

        this.applicationContext =applicationContext;
        this.sample = sample;
        this.jsonModels =jsonModels;

    }


    @Override
    public int getCount() {
        return jsonModels.size();
    }

    @Override
    public Object getItem(int i) {
        return jsonModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view =  layoutInflater.inflate(R.layout.sample,viewGroup,false);

        }

        TextView name,grade,section;

        name= view.findViewById(R.id.name);
        grade=view.findViewById(R.id.grade);
        section=view.findViewById(R.id.section);


        name.setText(jsonModels.get(i).getName());
        grade.setText(jsonModels.get(i).getGrade());
        section.setText(jsonModels.get(i).getSection());

        return view;
    }
}