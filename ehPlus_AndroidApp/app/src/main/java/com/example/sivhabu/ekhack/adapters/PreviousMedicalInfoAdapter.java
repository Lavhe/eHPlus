package com.example.sivhabu.ekhack.adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.model.Reminder;


/**
 * Created by root on 11/5/17.
 */

public class PreviousMedicalInfoAdapter    extends RecyclerView.Adapter<PreviousMedicalInfoAdapter.PreviousMedicalInfoViewHolder>{


    private List<Reminder> list;

    public PreviousMedicalInfoAdapter(List<Reminder> list) {
        this.list = list;

    }

    @Override
    public PreviousMedicalInfoAdapter.PreviousMedicalInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifaction, parent, false);
        return new PreviousMedicalInfoAdapter.PreviousMedicalInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PreviousMedicalInfoAdapter.PreviousMedicalInfoViewHolder holder, int position) {

        final Reminder calendar = list.get(position);


        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(calendar.Timestamp);
        final int year = cal.get(Calendar.YEAR);
        final int day =  cal.get(Calendar.DAY_OF_MONTH);
        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String monthThree = month.substring(0, Math.min(month.length(), 3));

        long msDiff = calendar.date.getTime() - Calendar.getInstance().getTimeInMillis();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

        holder.txtYear.setText(String.valueOf(year));
        holder.txtMonth.setText(monthThree);
        holder.txtDay.setText(String.valueOf(day));
        holder.txtTitle.setText(calendar.Title);
        holder.txtTime.setText(new SimpleDateFormat("HH:mm").format(cal.getTime()));

        //rand color for notification card view
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.layoutDate.setCardBackgroundColor(color);

        if (daysDiff >= 0) {

            Log.i("dayssss",holder.txtYear.getText() + " : " + year );

            String description = calendar.Description;
            final String timeString = new SimpleDateFormat("HH:mm").format(calendar.date.getTime());

            if (description.length() > 25) {
                holder.txtDescription.setText( calendar.Description.substring(0,25) + "...");


            } else {
                holder.txtDescription.setText(calendar.Description);
            }

        }
        else //if previous event dont displat
        {
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PreviousMedicalInfoViewHolder extends RecyclerView.ViewHolder
    {

        public TextView txtMonth;
        public TextView txtDay;
        public TextView txtYear;
        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtTime;
        public CardView layoutDate;
        public RelativeLayout rlCalender;


        public PreviousMedicalInfoViewHolder(View itemView) {
            super(itemView);


            txtMonth = (TextView) itemView.findViewById(R.id.txtMonth);
            txtDay = (TextView) itemView.findViewById(R.id.txtDay);
            txtYear = (TextView) itemView.findViewById(R.id.txtYear);
            txtTitle = (TextView) itemView.findViewById(R.id.lblName);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            layoutDate = (CardView) itemView.findViewById(R.id.imgUserPP);
            rlCalender = (RelativeLayout) itemView.findViewById(R.id.rlCalender);
        }

    }
}
