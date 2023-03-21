package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;

public class Management {
    private ArrayList<Event> events;
    private ArrayList<User> users;


    public Management() {
        this.users = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private void setMembers() {

    }

    public boolean authenticate(String username, String password) {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user :
                users) {
            usernames.add(user.getUsername());
        }
        if (!usernames.isEmpty()) {
            return usernames.contains(username);
        }
        return false;
    }
}
