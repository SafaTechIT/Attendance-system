package com.example.attendancesystem.api.v1;


import com.example.attendancesystem.controller.Admin;
import com.example.attendancesystem.model.Event;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(produces = "application/json")
public class ClassAPI {

    @GetMapping(path = "/api/v1/classes/get-class")
    public String getClasses() {
        JSONObject object = new JSONObject();
        for (Event event :
                Admin.showClasses()) {
            object.put("id", event.getId());
            object.put("Title", event.getTitle());
            object.put("Date", event.getDate());
            object.put("Members", event.getMembers());
        }
        return object.toString();
    }

}
