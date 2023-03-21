package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;

public class Responsible extends User {


    public Responsible(String username, String password, String name, int role) {
        super(username, password, name, role);
    }

    public int addMember(Event event, ArrayList<String> members) {
        event.setMembers(members);
        return 0;
    }
}
