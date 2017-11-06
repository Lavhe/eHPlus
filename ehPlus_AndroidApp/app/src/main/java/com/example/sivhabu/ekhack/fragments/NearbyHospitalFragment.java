package com.example.sivhabu.ekhack.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.map.MapsActivity;

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
        ((MainActivity) getActivity()).setTitle("Hospitals/Clinics Near By");//sets action bar title
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("fromNearByHospital","true");
                startActivity(intent);
            }
        });
        return  view;
    }

}
