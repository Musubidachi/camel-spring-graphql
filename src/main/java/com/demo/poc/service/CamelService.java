package com.demo.poc.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CamelService {

    private final ProducerTemplate producerTemplate;

    public String callWebService(String payload) {
        return producerTemplate.requestBody("direct:startWebServiceCall", payload, String.class);
    }
    
    public String callSoapService(String payload) {
        // Replace the URI below with the direct route for the SOAP service
        return producerTemplate.requestBody("direct:startSoapServiceCall", payload, String.class);
    }
}

