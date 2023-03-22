package com.example.attendancesystem.database;

import com.example.attendancesystem.model.Event;

import java.util.ArrayList;

public class DatabaseDriver {
    private final ArrayList<Event> events;

    public DatabaseDriver() {
        events = new ArrayList<>();
    }

    //  TODO get event from database
    public ArrayList<Event> get() {
        return this.events;
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
