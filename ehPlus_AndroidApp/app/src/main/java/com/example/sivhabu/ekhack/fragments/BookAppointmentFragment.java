package com.example.sivhabu.ekhack.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.map.MapsActivity;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.Calendar;

public class BookAppointmentFragment extends Fragment  {

    private View view;
    private SearchableSpinner lblService,lblTImeSlot;
    private String[] adapterServices, adpaterTimeSlot;
    private TextView lblToBring;
    private Button btnDate;
    private static TextView lblDateTime;
    private static Button btnBranch;
    private SharedPreferences pref;

    //datetime picker stuff
    private View btnPickDate;
    private View btnPickTime;
    private TextView textView;


    public BookAppointmentFragment() {
        // Required empty public constructor
    }

    public  static  TextView getLblDateTime() {
        return lblDateTime;
    }

    public static Button getBtnBranch() {
        return btnBranch;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =  inflater.inflate(R.layout.fragment_book_appointment, container, false);
        ((MainActivity) getActivity()).setTitle("Book An Appointment");//sets action bar title

        //gui init
        lblService = (SearchableSpinner) view.findViewById(R .id.lblService);
        lblTImeSlot = (SearchableSpinner) view.findViewById(R.id.lblTImeSlot);
        lblToBring = (TextView) view.findViewById(R.id.lblToBring);
        //txtToDeposit = (EditText) view.findViewById(R.id.txtToDeposit);
        textView = (TextView) view.findViewById(R.id.text);
        lblDateTime = (TextView) view.findViewById(R.id.lblDateTime);
        btnBranch = (Button) view.findViewById(R.id.btnBranch);

        lblToBring.setVisibility(View.GONE);

        //Spinner things
        adapterServices = new String[] {
                "Please select a service",
                "Baby Checkup",
                "HIV Testing",
                "Medication Collection",
                "Optermertrist",
                "See Dentist",
                "See Doctor",
                "X-Ray",
                "Other",

        };

        lblService.setTitle("Select a service");
        lblService.setPositiveButton("OK");
        ArrayAdapter<String> uniCampusAdapater = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, adapterServices);
        lblService.setAdapter(uniCampusAdapater);

        lblService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //  String selectedItem = parent.getItemAtPosition(position).toString();
                if(parent.getSelectedItemPosition() ==0)
                {
                    Toast.makeText(getContext(),"Please select a service",Toast.LENGTH_SHORT).show();
                    lblToBring.setVisibility(View.GONE);
                }
                else if(parent.getSelectedItemPosition() == 1)//baby checkup
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("Please bring a certified affidavit proving that you're the child's biological parent or legal guardian");
                }
                else if(parent.getSelectedItemPosition() == 2) //HIV Testing
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("Always remember to condomize and avoid having multiple sexual partners");
                }
                else if(parent.getSelectedItemPosition() == 3)//Medication Collection
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("This will be very quick");
                }
                else if(parent.getSelectedItemPosition() == 4)//Optermertrist
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("Please note as from February 2017, optermertrists don't wprk on Sunday" );
                }
                else if(parent.getSelectedItemPosition() == 5)//See Dentist
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("");
                }
                else if(parent.getSelectedItemPosition() == 6)//See Doctor
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("");
                }
                else if(parent.getSelectedItemPosition() == 7)//xray
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("");
                }
                else if(parent.getSelectedItemPosition() == 8)//xray
                {
                    lblToBring.setVisibility(View.VISIBLE);
                    lblToBring.setText("Please specify reason");
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        //Spinner things
        adpaterTimeSlot = new String[] {
                "Please select a timeslot",
                "11:05 - 11:30",
                "11:32 - 11:50",
                "11:52 - 12:03",
                "13:32 - 14:12",

        };

        lblTImeSlot.setTitle("Select a time slot");
        lblTImeSlot.setPositiveButton("OK");
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, adpaterTimeSlot);
        lblTImeSlot.setAdapter(timeAdapter);

        btnDate = (Button) view.findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });


        //go tp map
        btnBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*      FragmentManager fragmentManager = getFragmentManager();
                FragmentActivity fragment = new MapsActivity();
                fragmentManager.beginTransaction().replace(R.id.main, fragment).commit();*/

                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });


        //set dest text


        //btnSubmit
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return  view;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date =  day+"-"+(intToMonth(month)) + "-"+year;
            BookAppointmentFragment.getLblDateTime().setText(date);
        }
    }


    private static String intToMonth(int month)
    {
        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }
}
