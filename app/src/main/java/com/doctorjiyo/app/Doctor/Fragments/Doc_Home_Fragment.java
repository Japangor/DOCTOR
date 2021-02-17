package com.doctorjiyo.app.Doctor.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doctorjiyo.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Doc_Home_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doc__home, container, false);



        return view;
    }

}
