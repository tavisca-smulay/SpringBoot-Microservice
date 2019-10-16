package com.tavisca.gce.springboot.requestcaller.service;

import com.tavisca.gce.springboot.requestcaller.repository.Repository;
import com.tavisca.gce.springboot.requestcaller.repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestService {
    @Autowired
    Repository repository;

    public boolean storeAndValidateUsers(User user) {
        boolean isValidUser = false;
        String emailID = user.getEmailID();
        if(emailID.contains(".com") && emailID.contains("@")){
            isValidUser = true;
            repository.save(toEntity(user));
        }
        return isValidUser;
    }

    private User toEntity(User user) {
        User newUser = new User();
        newUser.setEmailID(user.getEmailID());
        newUser.setPassword(user.getPassword());
        newUser.setUserName(user.getUserName());
        newUser.setAge(user.getAge());
        return newUser;
    }
}
