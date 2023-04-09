package com.example.attendancesystem.controller;

import com.example.attendancesystem.database.UserDriver;

// Role 1
public class Management {
    private static UserDriver driver;

    public Management() {
        driver = new UserDriver();
    }

    public boolean correctUsername(String username) {
        for (User user: driver.getUsers()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static User authenticate(String username, String password) {
        driver = new UserDriver();
        for (User user : driver.getUsers())
            if (user.getHashMap().get(username) != null)
                if (user.getHashMap().get(username).equals(password)) return user;
        return null;
    }
}
