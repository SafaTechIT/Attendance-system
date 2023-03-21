package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends User{


    public Admin(String username, String password, String name, int role) {
        super(username, password, name, role);
    }

    public int addEvent(String title, Date date) {
        Event event = new Event(title, date);
        return 0;
    }
}
