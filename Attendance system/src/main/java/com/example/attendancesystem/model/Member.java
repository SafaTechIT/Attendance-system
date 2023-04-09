package com.example.attendancesystem.model;

import java.util.HashMap;

public class Member {
    private int id;
    private final String username;
    private final HashMap<String, String> hashMap;
    private final String name;
    private final Integer role;

    public Member(String username, String password, String name, int role) {
        this.username = username;
        this.hashMap = new HashMap<>();
        hashMap.put(username, password);
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

}
