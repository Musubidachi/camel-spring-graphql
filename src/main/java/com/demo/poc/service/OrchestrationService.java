package com.demo.poc.service;

import org.springframework.stereotype.Service;

import com.demo.poc.model.RandomUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrchestrationService {

    private final CamelService camelService;
    private final ObjectMapper objectMapper;
    
    // Method to handle the GET request logic
    public String handleGetRequest() {
        // Call Camel route (e.g., using ProducerTemplate)
        // Call the secondary REST service (e.g., using RestTemplate or WebClient)
        // Combine and process the results
        return camelService.callWebService("Sample payload");
    }

    // Method to handle the POST request logic
    public String handlePostRequest(String requestData) {
        // Call Camel route (e.g., using ProducerTemplate)
        // Call the secondary REST service (e.g., using RestTemplate or WebClient)
        // Combine and process the results
        return camelService.callWebService("Sample payload");
    }
    
    public RandomUserResponse getRandomUserFromCamel() {
    	try {
            String jsonResponse = camelService.callWebService("Sample payload");
            return objectMapper.readValue(jsonResponse, RandomUserResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch activity from Camel", e);
        }
    }
}

