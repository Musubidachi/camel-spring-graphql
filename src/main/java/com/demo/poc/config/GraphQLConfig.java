package com.demo.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.demo.poc.fetcher.OrchestrationDataFetcher;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(
            OrchestrationDataFetcher orchestrationDataFetcher) {
        return wiring -> wiring
            .type("Query", typeWiring -> typeWiring
                .dataFetcher("orchestrateServices", orchestrationDataFetcher));
    }
}

