package com.example.attendancesystem.api;

import com.example.attendancesystem.controller.Management;
import com.example.attendancesystem.controller.User;
import com.example.attendancesystem.database.UserDriver;
import org.json.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(produces = "application/json")
public class UserAPI {

    @PostMapping(path = "/api/v1/users/login-auth")
    public int authentication(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        if (Management.authenticate(username, UserDriver.encrypt(password))) return 0;
        return -1;
    }

    @GetMapping(path = "/api/v1/get-user")
    public ArrayList<User> getTest() {
        return User.getUsers();
    }
}
