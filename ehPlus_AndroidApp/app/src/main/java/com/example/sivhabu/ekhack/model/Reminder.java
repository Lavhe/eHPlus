package com.example.sivhabu.ekhack.model;

import java.util.Date;

/**
 * Created by root on 11/5/17.
 */

public class Reminder {

    public int Res_Id;
    public String Description;
    public String Title;
    public String Deleted;
    public long Timestamp;
    public Date date;

    public Reminder() {
    }
/*
    public Reminder(int res_Id, String description, String title, String deleted, long timestamp) {
        Res_Id = res_Id;
        Description = description;
        Title = title;
        Deleted = deleted;
        Timestamp = timestamp;
    }*/

    public Reminder(int res_Id, String description, String title, String deleted, long timestamp, Date date) {
        Res_Id = res_Id;
        Description = description;
        Title = title;
        Deleted = deleted;
        Timestamp = timestamp;
        this.date = date;
    }
}
