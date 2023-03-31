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
    public void testGetAll() {
        ArrayList<Event> events = driver.getEvents();
        System.out.println("ID\tTitle\tDate\tMembers");
        for (Event event : events) {
            System.out.println(event.getId() + "\t" + event.getTitle() + "\t" + event.getDate() + "\t" + event.getMembers());
        }
    }

    @Test
    public void testGet() {
        Event event = driver.getEvent(5);
        System.out.println("ID\tTitle\tDate\tMembers");
        System.out.println(event.getId() + "\t" + event.getTitle() + "\t" + event.getDate() + "\t" + event.getMembers());

    }

    @Test
    public void insertTest() {
        assertTrue(driver.insert(new Event("Class", new Date(System.currentTimeMillis()))));
    }

    @Test
    public void testUpdate() {
        Event newEvent = new Event("Subject", new Date(System.currentTimeMillis()));
        ArrayList<String> member = new ArrayList<>();
        member.add("User 1");
        member.add("User 2");
        member.add("User 3");
        member.add("User 4");
        member.add("User 5");
        member.add("User 7");
        newEvent.setMembers(member);
        assertTrue(driver.update(5, newEvent));
    }

    @Test
    public void testDelete() {
        Event event = new Event("title", new Date(System.currentTimeMillis()));
        if (driver.insert(event)) assertTrue(driver.delete(event.getId()));
    }
}
