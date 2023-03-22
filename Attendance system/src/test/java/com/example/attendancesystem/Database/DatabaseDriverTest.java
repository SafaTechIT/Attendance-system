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
        eventCreator();
        ArrayList<Event> events = driver.get();
        for (Event event : events) {
            assertEquals("title\t2023-03-22",
                    event.getTitle() + "\t" + event.getDate());
        }
    }

    @Test
    public void eventCreator() {
        assertTrue(driver.insert(new Event("title", new Date(System.currentTimeMillis()))));
    }

}
