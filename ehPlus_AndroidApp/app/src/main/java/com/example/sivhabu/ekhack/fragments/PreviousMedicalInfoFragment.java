package com.example.sivhabu.ekhack.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.adapters.PreviousMedicalInfoAdapter;
import com.example.sivhabu.ekhack.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousMedicalInfoFragment extends Fragment {


    private RecyclerView rvNotificationList;
    private PreviousMedicalInfoAdapter uAdapter;
    private List<Reminder> list;
    private View view;

    public PreviousMedicalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_previous_medical_info, container, false);

        rvNotificationList = view.findViewById(R.id.notification_list);
        list = new ArrayList<>();


        try {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    String dateInString = "30-08-2016 10:20:53";
    Date d1 = sdf.parse(dateInString);

    String dateInString1 = "28-03-2014 11:55:41";
    Date d2 = sdf.parse(dateInString1);

    String dateInString3 = "09-01-2017 13:25:17";
    Date d3 = sdf.parse(dateInString3);

    String dateInString4 = "16-06-2017 10:20:30";
    Date d4 = sdf.parse(dateInString4);



    Reminder r1 =new Reminder(0, "Stroke on left side of the body","Check Up", "No",1506890, d1);
    list.add(r1);

    Reminder r2 =new Reminder(1, "Knee bleeding internally", "X-Ray Scan on knee","No" , 1506890116,d2 );
    list.add(r2);

    Reminder r3 =new Reminder(2, "Schedules for ARVs", "Collect ARVs","No", 1506890116, d3);
    list.add(r3);

    Reminder r4 =new Reminder(3, "Suffering flu for more than two weeks ","Coughing out blood" ,"No", 150689011, d4);
    list.add(r4);

    Reminder r5 =new Reminder(4, "Circumsion is not healing ","Circumcision", "No",1506890116, d4);
    list.add(r5);

    Reminder r6 =new Reminder(5, " Am always sneezing when I got to sleep", "Sinus", "No",1506890116, d4);
    list.add(r6);


            rvNotificationList.setVisibility(View.VISIBLE);
            uAdapter = new PreviousMedicalInfoAdapter(list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    rvNotificationList.setLayoutManager(mLayoutManager);
    rvNotificationList.setItemAnimator(new DefaultItemAnimator());
    rvNotificationList.setAdapter(uAdapter);
    uAdapter.notifyDataSetChanged();


}catch (Exception e)
{
    e.printStackTrace();

}

        return view;
    }

}
