package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.sql.Date;
import java.util.ArrayList;

public class Admin extends User {

    public Admin(String username, String password, String name, int role) {
        super(username, password, name, role);
    }

    public int addEvent(String title, Date date) {
        Event event = new Event(title, date);
        if (getDriver().insert(event)) return 0;
        return -1;
    }

    public ArrayList<Event> showClasses() {
        return getDriver().getEvents();
    }

    public int delete(Event event) {
        if (getDriver().delete(event.getId())) return 0;
        return -1;
    }
}
