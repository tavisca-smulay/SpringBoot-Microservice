package com.tavisca.gce.springboot.initialrequest.service;

import com.tavisca.gce.springboot.initialrequest.model.InputRequest;
import com.tavisca.gce.springboot.initialrequest.repository.RequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class InputGatewayService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    public InputRequest saveToDB(JSONObject userJSON) {
        InputRequest request = new InputRequest();
        String transactionId = UUID.randomUUID().toString();
        String toServiceName = getToServiceName();
        request.setTransactionId(transactionId);
        request.setServiceName("Input Gateway");
        request.setInstant(Instant.now());
        request.setToService(toServiceName);
        request.setFromService(httpServletRequest.getHeader("user-agent"));
        request.setValid(true);
        request.setUser(userJSON.toString());
        requestRepository.save(request);
        return request;
    }

    private String getToServiceName() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = restTemplate.headForHeaders("http://localhost:1002/currentServiceName");
        List<String> serviceNameList = httpHeaders.get("service_name");
        if (serviceNameList.size() == 1)
            return serviceNameList.get(0);
        return null;
    }

    public Boolean sendToNextService(InputRequest request) {
        final String uri = "http://localhost:1002/requestCaller";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(uri, request.toString(), String.class);
        if (responseEntity.getStatusCode().isError()) return false;
        return true;
    }
}