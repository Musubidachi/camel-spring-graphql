package com.demo.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrchestratedResponse {
    private RandomUserResponse randomUserResponse;
    private String soapResponse;
}

