package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;

// Role 2
public class Responsible extends User {


    public Responsible(String username, String password, String name, int role) {
        super(username, password, name, role);
    }

    public int addMember(Event event, ArrayList<String> members) {
        Event newEvent = new Event(event.getTitle(), event.getDate());
        newEvent.setMembers(members);

        // TODO Database driver
        if (getDriver().update(event, newEvent)) return 0;
        return -1;
    }
}
