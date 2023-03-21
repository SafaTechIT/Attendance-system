package com.example.attendancesystem.controller;

import java.util.HashMap;

public class User {

    private final String username;
    private final HashMap<String, String> hashMap;
    private final String name;
    private final int role;

    public User(String username, String password, String name, int role) {
        this.hashMap = new HashMap<>();
        this.username = username;
        hashMap.put(username, password);
        this.name = name;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }
}
