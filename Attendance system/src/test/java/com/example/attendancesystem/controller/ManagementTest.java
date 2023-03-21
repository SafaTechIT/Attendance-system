package com.example.attendancesystem.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagementTest {

    private final Management management;

    public ManagementTest() {
        management = new Management();
    }

    @Test
    public void authenticate() {
        String username = "username";
        String password = "password";

        userCreator();
        assertTrue(management.authenticate(username, password));
    }

    private void userCreator() {
        String username = "username";
        String password = "password";
        String name = "name";
        int role = 0;

        for (int i = 0; i < 10; i++) {
            String str = "" + username + i;
            User user = new User(str, password, name, role);
            management.setUsers(user);
        }
        User user = new User(username, password, name, role);
        management.setUsers(user);
    }
}
