package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;

// Role 1
public class Management {
    private ArrayList<Event> events;
    private final ArrayList<User> users;


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

    public void setUsers(User user) {
        this.users.add(user);
    }

    private void setMembers() {

    }

    public boolean authenticate(String username, String password) {
        for (User user : users)
            if (user.getHashMap().get(username) != null)
                if (user.getHashMap().get(username).equals(password)) return true;
        return false;
    }
}
