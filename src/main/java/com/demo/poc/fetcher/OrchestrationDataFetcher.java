package com.demo.poc.fetcher;

import org.springframework.stereotype.Component;

import com.demo.poc.model.OrchestratedResponse;
import com.demo.poc.service.OrchestrationService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrchestrationDataFetcher implements DataFetcher<OrchestratedResponse> {

    private final OrchestrationService orchestrationService;

    @Override
    public OrchestratedResponse get(DataFetchingEnvironment environment) {
        // Extract arguments from the query
        String message = environment.getArgument("message");

        // Call the orchestration logic
        return orchestrationService.orchestrateServices(message, environment);
    }
}

