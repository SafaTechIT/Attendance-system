package com.example.attendancesystem.database;

import com.example.attendancesystem.model.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseDriver {
    private final ArrayList<Event> events;

    public DatabaseDriver() {
        events = new ArrayList<>();
    }

    private void connector() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db",
                    "mydbuser",
                    "mydbuser");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from designation");
        } catch (Exception ignore) {

        }
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
