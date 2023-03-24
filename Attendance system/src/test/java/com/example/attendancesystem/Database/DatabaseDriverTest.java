package com.example.attendancesystem.Database;

import com.example.attendancesystem.database.DatabaseDriver;
import com.example.attendancesystem.model.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.sql.Date;

public class DatabaseDriverTest {

    private final DatabaseDriver driver;

    public DatabaseDriverTest() {
        driver = new DatabaseDriver();
    }

    @Test
    public void testGet() {
        insertTest();
        ArrayList<Event> events = driver.get();
        for (Event event : events) {
            assertEquals("title\t2023-03-22",
                    event.getTitle() + "\t" + event.getDate());
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
        for (Event ev : driver.get()) {
            System.out.println(ev.getTitle() + "\t" + ev.getDate() + "\t" + ev.getMembers().size());
        }
    }

    @Test
    public void testDelete() {
        Event event = new Event("title", new Date(System.currentTimeMillis()));
        if (driver.insert(event)) assertTrue(driver.delete(event));
    }
}
