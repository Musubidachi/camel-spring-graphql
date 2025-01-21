package com.demo.poc.service;

import org.springframework.stereotype.Service;

import com.learnwebservices.services.hello.HelloRequest;

@Service
public class SoapService {

    public HelloRequest createSoapPayload(String message) {
        HelloRequest request = new HelloRequest();
        request.setName(message);
        return request;
    }
}

