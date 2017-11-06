package com.example.sivhabu.ekhack.model;

/**
 * Created by root on 11/4/17.
 */

public class User {


    public String surname;
    public String address;
    public String gender;
    public String name;
    public String pp;
    public String firebasetoken;
    public long id_no;
    public int role;
    public Allergies[] allergies;


    public User() {
    }

    public User(String surname, String address, String gender, String name, String pp, String firebasetoken, long id_no, int role, Allergies[] allergies) {
        this.surname = surname;
        this.address = address;
        this.gender = gender;
        this.name = name;
        this.pp = pp;
        this.firebasetoken = firebasetoken;
        this.id_no = id_no;
        this.role = role;
        this.allergies = allergies;
    }
}
