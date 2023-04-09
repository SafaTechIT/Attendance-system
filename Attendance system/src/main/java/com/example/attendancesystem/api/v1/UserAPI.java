package com.example.attendancesystem.api.v1;

import com.example.attendancesystem.controller.Management;
import com.example.attendancesystem.controller.User;
import com.example.attendancesystem.database.UserDriver;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(produces = "application/json")
public class UserAPI {

    @PostMapping(path = "/api/v1/users/login-auth")
    public ResponseEntity<String> authentication(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        User user = Management.authenticate(username, UserDriver.encrypt(password));
        JSONObject object = new JSONObject();
        if (user != null) {
            int id = user.getId();
            int role = user.getRole();
            String name = user.getName();
            object.put("id", id);
            object.put("name", name);
            object.put("username", username);
            object.put("role", role);
            return new ResponseEntity<>(object.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/api/v1/users/get-user")
    public ArrayList<User> getUsers() {
        return User.getUsers();
    }

    @PostMapping(path = "/api/v1/users/add-user")
    public ResponseEntity<String> addUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "name") String name) {
        JSONObject object = new JSONObject();
        if (Management.newUser(username, password, name, 2)) {
            object.put("username", username);
            object.put("name", name);
            return new ResponseEntity<>(object.toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(object.toString(), HttpStatus.NOT_MODIFIED);
    }
}
