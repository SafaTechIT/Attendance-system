package com.example.attendancesystem.Database;

import com.example.attendancesystem.controller.User;
import com.example.attendancesystem.database.UserDriver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class UserDriveTest {

    private final UserDriver drive;

    public UserDriveTest() {
        this.drive = new UserDriver();
    }

    @Test
    public void testGet() {
        ArrayList<User> users = drive.getUsers();
        for (User user :
                users) {
            System.out.println(user.getName());
        }
    }
}
