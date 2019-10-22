package com.tavisca.gce.springboot.finalrequesthandler;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Service
public class FinalService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    public void saveToDB(JSONObject user) {
        User userData = new User();
        userData.setAge(user.getInt("age"));
        userData.setEmailID(user.getString("emailID"));
        userData.setTransactionId(user.getString("transactionId"));
        userData.setInstant(Instant.now());
        userData.setPassword(user.getString("password"));
        userData.setUserName(user.getString("userName"));
        userData.setFromService(httpServletRequest.getHeader("user-agent"));
        userData.setServiceName("Final Service");
        userRepository.save(userData);
    }
}