package com.tavisca.gce.springboot.requestcaller.repository;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="transaction_id", nullable = false, length = 150)
    private String transactionId;
    @Basic
    @Column(name = "timestamp", nullable = false, length = 150)
    private Instant instant;
    @Basic
    @Column(name = "service_name", nullable = false, length = 255)
    private String serviceName;
    @Basic
    @Column(name="email_id", nullable = false, length = 30)
    private String emailID;
    @Basic
    @Column(name = "password", nullable = false, length = 15)
    private String password;
    @Basic
    @Column(name = "user_name", nullable = false, length = 15)
    private String userName;
    @Basic
    @Column(name = "age", nullable = false, length = 3)
    private int age;
    @Basic
    @Column(name = "to_service", nullable = false, length = 255)
    private String toService;
    @Basic
    @Column(name = "from_service", nullable = false, length = 255)
    private String fromService;

    public User() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getToService() {
        return toService;
    }

    public void setToService(String toService) {
        this.toService = toService;
    }

    public String getFromService() {
        return fromService;
    }

    public void setFromService(String fromService) {
        this.fromService = fromService;
    }

    @Override
    public String toString() {
        return "{" +
                "transactionId:'" + transactionId + '\'' +
                ", instant:'" + instant + '\'' +
                ", serviceName:'" + serviceName + '\'' +
                ", emailID:'" + emailID + '\'' +
                ", password:'" + password + '\'' +
                ", userName:'" + userName + '\'' +
                ", age:'" + age + '\'' +
                ", toService:'" + toService + '\'' +
                ", fromService:'" + fromService + '\'' +
                '}';
    }
}