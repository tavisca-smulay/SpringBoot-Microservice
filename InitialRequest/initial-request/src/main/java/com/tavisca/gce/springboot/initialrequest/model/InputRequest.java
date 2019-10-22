package com.tavisca.gce.springboot.initialrequest.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="input_request")
public class InputRequest {
    @Id
    @Column(name="transaction_id", nullable = false, length =50)
    private String transactionId;
    @Basic
    @Column(name = "user", nullable = false, length = 255)
    private String user;
    @Basic
    @Column(name = "timestamp", nullable = false, length = 150)
    private Instant instant;
    @Basic
    @Column(name = "service_name", nullable = false, length = 255)
    private String serviceName;
    @Basic
    @Column(name = "is_valid", nullable = false, length = 2)
    private Boolean isValid;
    @Basic
    @Column(name = "to_service", nullable = false, length = 255)
    private String toService;
    @Basic
    @Column(name = "from_service", nullable = false, length = 255)
    private String fromService;

    public InputRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
                "transactionId : '" + transactionId + '\'' +
                ", user : '" + user + '\'' +
                ", instant : '" + instant + '\'' +
                ", serviceName : '" + serviceName + '\'' +
                ", isValid : '" + isValid + '\'' +
                ", toService : '" + toService + '\'' +
                ", fromService : '" + fromService + '\'' +
                '}';
    }
}