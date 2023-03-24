package com.example.attendancesystem.database;

import com.example.attendancesystem.model.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventsDriver extends Driver {
    private final ArrayList<Event> events;

    public EventsDriver() {
        events = new ArrayList<>();
        this.get(getStatement());
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    //  TODO get event from database
    @Override
    protected void get(Statement st) {
        try {
            ResultSet rs = st.executeQuery("select * from events");
            String title;
            Date date;
            ArrayList<String> members;
            while (rs.next()) {
                title = rs.getString("title");
                date = Date.valueOf(rs.getString("date"));
                Event event = new Event(title, date);
                members = memberExtraction(rs.getString("members"));
                event.setMembers(members);
                events.add(event);
            }
            rs.close();
            closeAnything();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private ArrayList<String> memberExtraction(String str) {
        ArrayList<String> members = new ArrayList<>();
        str = str.substring(0, str.length() - 1);
        str = str.substring(1);
        String[] newStr = str.split(", ");
        for (String ar : newStr) {
            ar = ar.replace("\"", "");
            members.add(ar);
        }
        return members;
    }

    //  TODO insert event to database
    public boolean insert(Event event) {
        events.add(event);
        return true;
    }

    // TODO update event to database
    public boolean update(Event event, Event newEvent) {
        events.set(events.indexOf(event), newEvent);
        return true;
    }

    //  TODO delete event from database
    public boolean delete(Event event) {
        events.remove(event);
        return true;
    }
}
