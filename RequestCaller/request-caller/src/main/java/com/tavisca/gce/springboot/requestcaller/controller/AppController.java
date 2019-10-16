package com.tavisca.gce.springboot.requestcaller.controller;

import com.tavisca.gce.springboot.requestcaller.repository.User;
import com.tavisca.gce.springboot.requestcaller.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @Autowired
    RequestService requestService;

    @PostMapping(path = "api/v1/requestCaller/validateUser")
    public String storeAndValidateUsers(@RequestBody User user) {
        boolean validationResult = requestService.storeAndValidateUsers(user);
        if(validationResult == true)
            return "Valid User";
        else
            return "Invalid User";
    }

}
