package com.example.sivhabu.ekhack.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sivhabu.ekhack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByHospitalFragment extends Fragment {

    private View view;

    public NearByHospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_near_by_hospital, container, false);
        return  view;
    }

}
