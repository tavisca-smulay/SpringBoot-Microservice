package com.tavisca.gce.springboot.initialrequest.controller;

import com.tavisca.gce.springboot.initialrequest.model.InputRequest;
import com.tavisca.gce.springboot.initialrequest.service.InputGatewayService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @Autowired
    InputGatewayService inputGatewayService;

    @PostMapping(path = "/inputGateway")
        public ResponseEntity<?> validateAndSave(@RequestBody String requestData) {
            JSONObject userJSON = new JSONObject(requestData);
            if(userJSON.getString("userName") != null
                && userJSON.getInt("age") > 0
                && userJSON.getString("emailID") !=null
                && userJSON.getString("password") !=null){
                InputRequest request = inputGatewayService.saveToDB(userJSON);
                boolean isRequestSent = inputGatewayService.sendToNextService(request);
                if(isRequestSent)
                return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
                else return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        }
}
