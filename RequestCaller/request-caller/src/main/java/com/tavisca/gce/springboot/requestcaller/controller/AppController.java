package com.tavisca.gce.springboot.requestcaller.controller;

import com.tavisca.gce.springboot.requestcaller.repository.User;
import com.tavisca.gce.springboot.requestcaller.service.RequestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @Autowired
    RequestService requestService;

    @GetMapping(path = "/currentServiceName")
    public HttpHeaders getCurrentServiceName(@RequestHeader HttpHeaders httpHeaders)
    {
        httpHeaders.set("service_name",this.getClass().getName());
        return httpHeaders;
    }

    @PostMapping(path = "/requestCaller")
    public ResponseEntity<?> validate(@RequestBody String userRequest){
        JSONObject userJSONObject = new JSONObject(userRequest);
        User user = requestService.saveToDB(userJSONObject);
        boolean isValidated = requestService.validate(user);
        if(isValidated){
            boolean isRequestSent = requestService.sendToNextService(user);
            if(isRequestSent)
                return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
            else return new ResponseEntity<>("Failure Sending To Next Service", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Incorrect Data", HttpStatus.BAD_REQUEST);
        }
    }
}
