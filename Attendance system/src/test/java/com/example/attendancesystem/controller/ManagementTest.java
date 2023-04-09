package com.example.attendancesystem.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagementTest {

    private final Management management;

    public ManagementTest() {
        management = new Management();
    }

    @Test
    public void authenticate() {
        String username = "username";
        String password = "password";
        User user = Management.authenticate(username, password);
        if (user != null)
            assertEquals(1, user.getRole());
    }

    @Test
    public void incorrectUsername() {
        String username = "username";
        assertFalse(management.correctUsername(username));
    }

    @Test
    public void correctUsername() {
        String username = "username9";
        assertTrue(management.correctUsername(username));
    }

}
