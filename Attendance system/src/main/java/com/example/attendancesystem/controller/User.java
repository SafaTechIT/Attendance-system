package com.example.attendancesystem.controller;

import com.example.attendancesystem.database.EventsDriver;
import com.example.attendancesystem.database.UserDriver;
import com.example.attendancesystem.model.Member;

import java.util.ArrayList;

/**
 * Extend the Member class and implement the getDriver method.
 */
public class User extends Member {

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
    }

    public static ArrayList<User> getUsers() {
        return new UserDriver().getUsers();
    }
}
