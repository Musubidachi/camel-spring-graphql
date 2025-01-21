package com.demo.poc.service;

import org.springframework.stereotype.Service;

import com.demo.poc.model.OrchestratedResponse;
import com.demo.poc.model.RandomUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnwebservices.services.hello.HelloEndpointService;
import com.learnwebservices.services.hello.HelloRequest;
import com.learnwebservices.services.hello.HelloResponse;

import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrchestrationService {

    private final CamelService camelService;
    private final ObjectMapper objectMapper;
    private final SoapService soapService;
    
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
    
    public String getSayHelloResponse(String message) {
        // Call the SOAP service
        HelloEndpointService service = new HelloEndpointService();
        var port = service.getHelloEndpointPort();

        HelloRequest request = soapService.createSoapPayload(message);

        HelloResponse response = port.sayHello(request);

        return response.getMessage();
    }

    public OrchestratedResponse orchestrateServices(String message, DataFetchingEnvironment env) {
        OrchestratedResponse response = new OrchestratedResponse();

        try {
            // Check if the randomUserResponse field is requested or if it's needed for the subsequent call
            if (env.getSelectionSet().contains("randomUserResponse") || env.getSelectionSet().contains("soapResponse")) {
                String jsonResponse = camelService.callWebService("Sample payload");
                RandomUserResponse randomUserResponse = objectMapper.readValue(jsonResponse, RandomUserResponse.class);
                response.setRandomUserResponse(randomUserResponse);
            }

            // Check if the soapResponse field is requested
            if (env.getSelectionSet().contains("soapResponse")) {
                HelloEndpointService service = new HelloEndpointService();
                var port = service.getHelloEndpointPort();

                HelloRequest request = soapService.createSoapPayload(message);

                HelloResponse soapResponse = port.sayHello(request);
                response.setSoapResponse(soapResponse.getMessage());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to orchestrate services", e);
        }

        return response;
    }
}

