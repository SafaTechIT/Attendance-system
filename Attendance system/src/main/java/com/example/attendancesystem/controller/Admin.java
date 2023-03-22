package com.example.attendancesystem.controller;

import com.example.attendancesystem.database.DatabaseDriver;
import com.example.attendancesystem.model.Event;

import java.util.Date;

public class Admin extends User {

    public Admin(String username, String password, String name, int role) {
        super(username, password, name, role);
    }

    public int addEvent(String title, Date date) {
        Event event = new Event(title, date);

        // TODO Database driver for test Admin class
        if (getDriver().insert(event)) return 0;
        return -1;
    }

    public void showClasses() {
        // TODO Database driver for test Admin class
        System.out.println(getDriver().get());
    }

    public int delete(Event event) {
        // TODO Database driver for test Admin class
        if (getDriver().delete(event)) return 0;
        return -1;
    }
}
