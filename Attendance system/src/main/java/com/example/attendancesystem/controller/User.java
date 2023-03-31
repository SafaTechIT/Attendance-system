package com.example.attendancesystem.controller;

import com.example.attendancesystem.database.EventsDriver;
import com.example.attendancesystem.model.Member;

/**
 *  Extend the Member class and implement the getDriver method.
 */
public class User extends Member {
    private final EventsDriver driver;

    /**
     * Construct the instance with the given parameters and
     * create a new instance of EventsDriver for Users operations
     *
     * @param username Username of user that will be authenticated.
     * @param password String that will be hashed and mapped to the username.
     * @param name     Name of the user.
     * @param role     Role of the user; its Admin or not.
     */
    public User(String username, String password, String name, int role) {
        super(username, password, name, role);
        driver = new EventsDriver();
    }

    /**
     *  Get the driver of events for its operations.
     * **/
    public EventsDriver getDriver() {
        return driver;
    }
}
