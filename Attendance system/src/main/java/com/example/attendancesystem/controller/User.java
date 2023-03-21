package com.example.attendancesystem.controller;

public class User {

    private final String username;
    private final String password;
    private final String name;
    private final int role;

    public User(String username, String password, String name, int role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }
}
