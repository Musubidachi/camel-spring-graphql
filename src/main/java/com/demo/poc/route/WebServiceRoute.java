package com.demo.poc.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class WebServiceRoute extends RouteBuilder {

    @Override
    public void configure() {

        // SOAP service route
        from("direct:startSoapServiceCall")
            .doTry()
                .to("cxf://https://apps.learnwebservices.com/services/hello"
                    + "?wsdlURL=https://apps.learnwebservices.com/services/hello?wsdl"
                    + "&serviceClass=com.learnwebservices.services.hello.HelloEndpoint"
                    + "&dataFormat=POJO")
                .log("Response from SOAP service: ${body}")
            .doCatch(Exception.class)
                .log("Error calling SOAP service: ${exception.message}")
                .setBody(constant("SOAP service failed"))
            .end();

        // REST service route
        from("direct:startWebServiceCall")
            .doTry()
                .to("https://randomuser.me/api/?results=2")
                .log("Response from Random User API: ${body}")
            .doCatch(HttpOperationFailedException.class)
                .log("Error calling API: ${exception.message}")
                .setBody(constant("{\"results\":[], \"info\":{}}"))
            .end();

        // Process the response for Random User API
        from("direct:processResponse")
            .log("Processing Random User API response...")
            .transform(simple("Here's a Random User : ${body}"))
            .log("Final processed response: ${body}");
    }
}

