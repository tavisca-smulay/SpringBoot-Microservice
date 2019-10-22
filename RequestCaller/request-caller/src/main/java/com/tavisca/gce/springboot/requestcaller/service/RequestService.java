package com.tavisca.gce.springboot.requestcaller.service;

import com.tavisca.gce.springboot.requestcaller.repository.Repository;
import com.tavisca.gce.springboot.requestcaller.repository.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

@Component
public class RequestService {
    @Autowired
    Repository repository;
    @Autowired
    HttpServletRequest httpServletRequest;

    public User saveToDB(JSONObject userJSONObject) {
        User user = new User();
        String toServiceName = getToServiceName();
        user.setToService(toServiceName);
        user.setInstant(Instant.now());
        user.setFromService(httpServletRequest.getHeader("user-agent"));
        JSONObject newObject = new JSONObject(userJSONObject.getString("user"));
        user.setUserName(newObject.getString("userName"));
        user.setAge(newObject.getInt("age"));
        user.setPassword(newObject.getString("password"));
        user.setEmailID(newObject.getString("emailID"));
        user.setServiceName("Validation Service");
        user.setTransactionId(userJSONObject.getString("transactionId"));
        repository.save(user);
        return user;
    }

    private String getToServiceName() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = restTemplate.headForHeaders("http://localhost:1003/currentServiceName");
        List<String> serviceNameList = httpHeaders.get("service_name");
        if (serviceNameList.size() == 1)
            return serviceNameList.get(0);
        return null;
    }

    public boolean validate(User user) {
        boolean isUserNameValidated = this.validateUserName(user.getUserName());
        boolean isAgeValidated = this.validateAge(user.getAge());
        boolean isPasswordValidated = this.validatePassword(user.getPassword());
        boolean isEmailIDValidated = this.validateEmailID(user.getEmailID());
        return isUserNameValidated && isAgeValidated && isEmailIDValidated && isPasswordValidated;
    }

    public Boolean sendToNextService(User user) {
        final String uri = "http://localhost:1003/saveUser";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(uri, user.toString(), String.class);
        if (responseEntity.getStatusCode().isError()) return false;
        return true;
    }

    private boolean validateEmailID(String emailID) {
        if(emailID.contains("@") && emailID.contains(".com"))
            return true;
        return false;
    }

    private boolean validatePassword(String password) {
        if(password == null) return false;
        return true;
    }

    private boolean validateAge(int age) {
        if(age<100 && age>0) return true;
        return false;
    }

    private boolean validateUserName(String userName) {
        if(userName == null) return false;
        return true;
    }
}
