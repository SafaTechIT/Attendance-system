package com.example.attendancesystem.Database;

import com.example.attendancesystem.database.EventsDriver;
import com.example.attendancesystem.model.Event;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsDriverTest {

    private final EventsDriver driver;

    public EventsDriverTest() {
        driver = new EventsDriver();
    }

    @Test
    public void testGet() {
//        insertTest();
        ArrayList<Event> events = driver.getEvents();
        System.out.println("Title\tDate\tMembers");
        for (Event event : events) {
            System.out.println(event.getTitle() + "\t" + event.getDate() + "\t" + event.getMembers());
        }
    }

    @Test
    public void insertTest() {
        assertTrue(driver.insert(new Event("title", new Date(System.currentTimeMillis()))));
    }

    @Test
    public void testUpdate() {
        Event event = new Event("title", new Date(System.currentTimeMillis()));
        Event newEvent = new Event("Subject", new Date(System.currentTimeMillis()));
        driver.insert(event);
        assertTrue(driver.update(event, newEvent));
        System.out.println("Update:\nTitle\tDate\tMembers");
        for (Event ev : driver.getEvents()) {
            System.out.println(ev.getTitle() + "\t" + ev.getDate() + "\t" + ev.getMembers().size());
        }
    }

    @Test
    public void testDelete() {
        Event event = new Event("title", new Date(System.currentTimeMillis()));
        if (driver.insert(event)) assertTrue(driver.delete(event));
    }
}
