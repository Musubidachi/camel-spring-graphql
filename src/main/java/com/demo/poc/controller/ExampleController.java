package com.demo.poc.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.poc.model.OrchestratedResponse;
import com.demo.poc.service.OrchestrationService;

import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/example")
@AllArgsConstructor
public class ExampleController {

	private final OrchestrationService orchestrationService;

	// GET endpoint
	@GetMapping("/get-data")
	public ResponseEntity<String> getData() {
		String response = orchestrationService.handleGetRequest();
		return ResponseEntity.ok(response);
	}

	// POST endpoint
	@PostMapping("/post-data")
	public ResponseEntity<String> postData(@RequestBody String requestData) {
		String response = orchestrationService.handlePostRequest(requestData);
		return ResponseEntity.ok(response);
	}

	// GraphQL Query for Orchestration
	@QueryMapping
	public OrchestratedResponse orchestrateServices(@Argument String message, DataFetchingEnvironment environment) {
		return orchestrationService.orchestrateServices(message, environment);
	}

	// REST Endpoint for Orchestration (Optional)
	@GetMapping("/orchestrate")
	public ResponseEntity<OrchestratedResponse> getOrchestrateServices(@RequestParam String message,
			DataFetchingEnvironment environment) {
		OrchestratedResponse response = orchestrationService.orchestrateServices(message, environment);
		return ResponseEntity.ok(response);
	}
}
