package com.tavisca.gce.springboot.finalrequesthandler;

import jdk.jfr.internal.Repository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @Autowired
    UserRepository repository;
    @Autowired
    FinalService finalService;

    @GetMapping(path = "/currentServiceName")
    public HttpHeaders getCurrentServiceName(@RequestHeader HttpHeaders httpHeaders)
    {
        httpHeaders.set("service_name",this.getClass().getName());
        return httpHeaders;
    }

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        return (List<User>) repository.findAll();
    }

    @PostMapping(path = "/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody String user){
        JSONObject userJSON = new JSONObject(user);
        finalService.saveToDB(userJSON);
        return new ResponseEntity<>("User Saved", HttpStatus.ACCEPTED);
    }
}