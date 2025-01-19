package com.demo.poc.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
public class WebServiceRoute extends RouteBuilder {

	@Override
	public void configure() {
		// Define the route
		from("direct:startWebServiceCall")
	    .doTry()
	        .to("https://randomuser.me/api/?results=2")
	        .log("Response from Random User API: ${body}")
	    .doCatch(HttpOperationFailedException.class)
	        .log("Error calling API: ${exception.message}")
	        .setBody(constant("{\"results\":[], \"info\":{}}"))
	    .end();


		// Process the response
		from("direct:processResponse").log("Processing Random User API response...")
				.transform(simple("Here's a Random User : ${body}"))
				.log("Final processed response: ${body}");
	}
}
