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
@CrossOrigin(origins = "localhost:8080")
@RequestMapping(produces = "application/json")
public class UserAPI {

    @PostMapping(path = "/api/v1/users/login-auth")
    public ResponseEntity<String> authentication(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {

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
    public String getUsers() {
        JSONObject object = new JSONObject();
        for (User user :
                User.getUsers()) {
            object.put("id", user.getId());
            object.put("name", user.getName());
            object.put("role", user.getRole());
        }
        return object.toString();
    }

    @GetMapping(path = "/api/v1/users/{id}")
    public String getUser(@PathVariable int id) {
        User user = new UserDriver().getUser(id);
        JSONObject object = new JSONObject();
        object.put("id", user.getId());
        object.put("username", user.getUsername());
        object.put("name", user.getName());
        return object.toString();
    }

    @PostMapping(path = "/api/v1/users/add-user")
    public ResponseEntity<String> addUser(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "name") String name) {

        JSONObject object = new JSONObject();
        if (Management.newUser(username, password, name, 2)) {
            object.put("username", username);
            object.put("name", name);
            return new ResponseEntity<>(object.toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(object.toString(), HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(path = "/api/v1/users/put-user")
    public ResponseEntity<String> putUser(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "password") String password) {
        if (Management.updateUser(id, password)) {
            return new ResponseEntity<>("User updated.", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Can't update user.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/api/v1/users/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if (Management.deleteUser(id)) {
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Can't Delete user.", HttpStatus.BAD_REQUEST);
    }
}
