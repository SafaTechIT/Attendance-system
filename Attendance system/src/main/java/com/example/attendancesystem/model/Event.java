package com.example.attendancesystem.model;

import java.util.ArrayList;
import java.sql.Date;

public class Event {

    private int id;
    private final String title;
    private final Date date;
    private ArrayList<String> members;

    public Event(String title, Date date) {
        id = 0;
        this.title = title;
        this.date = date;
        this.members = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
