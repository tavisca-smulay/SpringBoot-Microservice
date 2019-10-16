package com.tavisca.gce.springboot.finalrequesthandler;

import jdk.jfr.internal.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @Autowired
    UserRepository repository;

    @GetMapping(path = "api/v1/requestHandler/getAllUsers")
    public List<User> getAllUsers(){
        return (List<User>) repository.findAll();
    }
}
