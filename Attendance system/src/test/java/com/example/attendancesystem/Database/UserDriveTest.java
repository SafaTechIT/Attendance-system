package com.example.attendancesystem.Database;

import com.example.attendancesystem.controller.User;
import com.example.attendancesystem.database.UserDriver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserDriveTest {

    private final UserDriver drive;

    public UserDriveTest() {
        this.drive = new UserDriver();
    }

    @Test
    public void testAllGet() {
        ArrayList<User> users = drive.getUsers();
        for (User user :
                users) {
            System.out.println(user.getId() + "\t" + user.getName() + "\t" + user.getUsername() + "\t" + user.getHashMap().get(user.getUsername()) + "\t" + user.getRole());
        }
    }

    @Test
    public void testGet() {
        User user = drive.getUser(22);
        System.out.println(user.getId() + "\t" + user.getName() + "\t" + user.getUsername() + "\t" + user.getHashMap().get(user.getUsername()) + "\t" + user.getRole());

    }

    @Test
    public void testInsert() {
        assertTrue(drive.insert(new User("username", "password", "user", 1)));
    }


    @Test
    public void testUpdate() {
        User user = new User("username", "password", "name", 1);
        drive.insert(user);

    }

    @Test
    public void testDelete() {
        assertTrue(drive.delete(8));
    }
}
