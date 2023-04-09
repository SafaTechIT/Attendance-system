package com.example.attendancesystem.api.v1;

import com.example.attendancesystem.controller.Management;
import com.example.attendancesystem.controller.User;
import com.example.attendancesystem.database.UserDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(produces = "application/json")
public class UserAPI {

    @PostMapping(path = "/api/v1/users/login-auth")
    public ResponseEntity<User> authentication(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        User user = Management.authenticate(username, UserDriver.encrypt(password));
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/api/v1/users/get-user")
    public ArrayList<User> getUsers() {
        return User.getUsers();
    }
}
