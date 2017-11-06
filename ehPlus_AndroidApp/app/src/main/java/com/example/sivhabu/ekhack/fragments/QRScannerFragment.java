package com.example.sivhabu.ekhack.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sivhabu.ekhack.R;
import com.google.android.gms.vision.barcode.Barcode;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class QRScannerFragment extends Fragment {

    Button scanbtn,btnScanId;
    TextView result;

    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;



    public QRScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_qrscanner, container, false);
        scanbtn = view.findViewById(R.id.start);
        result = view.findViewById(R.id.result);

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

               /* btnScanId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ScanActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                });*/
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                final Barcode barcode = data.getParcelableExtra("barcode");
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(barcode.displayValue);
                    }
                });
            }
        }
    }
}
