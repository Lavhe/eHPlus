package com.example.sivhabu.ekhack.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sivhabu.ekhack.AmbulanceMap.MapsActivity;
import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByAmbulanceFragment extends Fragment {

    private View view;
    public NearByAmbulanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_near_by_ambulance, container, false);

        ((MainActivity) getActivity()).setTitle("Ambulances Near By");//sets action bar title
        Button btn = (Button) view.findViewById(R.id.btnAmb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
