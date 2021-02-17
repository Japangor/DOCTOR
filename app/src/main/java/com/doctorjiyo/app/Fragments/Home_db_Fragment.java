package com.doctorjiyo.app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.doctorjiyo.app.R;


public class Home_db_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_home_db, container, false);

        String userid=this.getArguments().getString("userid");
        Toast.makeText(getContext(),userid, Toast.LENGTH_LONG).show();


        return  rootview;

    }
}
