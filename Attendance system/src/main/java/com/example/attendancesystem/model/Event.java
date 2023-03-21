package com.example.attendancesystem.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String title;
    private Date date;
    private ArrayList<String> members;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
        this.members = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
