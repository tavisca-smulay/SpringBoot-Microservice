package com.tavisca.gce.springboot.initialrequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppController {
    @PostMapping("/api/v1/initialRequest/send")
    public ResponseEntity sendToValidatorApi(@RequestBody User user) {
        final String uri = "http://localhost:9091/api/v1/requestCaller/validateUser";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(uri, user, String.class);
        if(responseEntity.getStatusCode().isError())
            return responseEntity;
        return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
    }
}
