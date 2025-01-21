
---

# Demo Project: Spring Boot + GraphQL + Camel + Swagger + SOAP

## Overview

This project demonstrates the integration of various modern technologies to build a Spring Boot application. It includes the following features:

- **Swagger**: API documentation and testing.
- **GraphQL**: Query and manipulate your API data efficiently.
- **Spring Boot**: Framework for building RESTful services.
- **Apache Camel**: Route orchestration and integration.
- **SOAP**: Integration with external SOAP web services.
- **GraphiQL**: In-browser GraphQL testing interface.

## Features

- REST endpoints documented with Swagger.
- GraphQL queries and mutations.
- Apache Camel routes for integration.
- SOAP service integration using Camel CXF.
- GraphiQL interface for testing GraphQL queries.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3.4.1**
- **GraphQL**
- **Swagger (SpringDoc OpenAPI)**
- **Apache Camel 4.9.0**
- **SOAP (CXF)**
- **GraphiQL**
- **Maven**

---

## Getting Started

### Prerequisites

- **Java 17**
- **Maven 3.x**
- **Eclipse IDE** (or any Java IDE of your choice)
- **Git**

### Clone the Repository

```bash
git clone https://github.com/Musubidachi/camel-spring-graphql.git
cd camel-spring-graphql
```

### Import the Project into Eclipse

1. Open Eclipse IDE.
2. Go to `File` > `Import`.
3. Select `Maven` > `Existing Maven Projects` and click `Next`.
4. Browse to the cloned repository folder and click `Finish`.
5. Eclipse will download the required dependencies and build the project.

---

## Running the Application

### Start the Service

1. Open your terminal or IDE.
2. Navigate to the project directory.
3. Run the following command to start the service:
   ```bash
   mvn spring-boot:run
   ```

Once started, the service will be available at `http://localhost:8080`.

---

## Accessing the Features

### Swagger UI

Swagger provides an interactive interface to test REST endpoints.

- URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### GraphQL Endpoint

Use GraphQL to query and manipulate data.

- Endpoint: [http://localhost:8080/graphql](http://localhost:8080/graphql)

### GraphiQL Interface

GraphiQL provides an in-browser interface for testing GraphQL queries.

- URL: [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql)

---

## Example Queries

### GraphQL Query for Random User and SOAP Responses:

```graphql
query {
    orchestrateServices(message: "Fetch Both") {
        randomUserResponse {
            results {
                name {
                    first
                    last
                }
            }
        }
        soapResponse
    }
}
```

### GraphQL Query for Only SOAP Response:

```graphql
query {
    orchestrateServices(message: "HelloSOAP") {
        soapResponse
    }
}
```

### GraphQL Query for Only Random User:

```graphql
query {
    orchestrateServices(message: "Fetch Random User") {
        randomUserResponse {
            results {
                name {
                    first
                    last
                }
            }
        }
    }
}
```

---

## Project Structure

```plaintext
src/
  main/
    java/
      com/demo/poc/
        controller/        # Controllers for REST and GraphQL endpoints
        service/           # Business logic and orchestration
        route/             # Apache Camel routes
    resources/
      graphql/             # GraphQL schema files
      static/              # Static resources (e.g., GraphiQL UI)
      application.properties # Application configuration
```

---

## SOAP Integration

### SOAP Endpoint:

- **WSDL**: `https://apps.learnwebservices.com/services/hello?wsdl`
- **Service**: `HelloEndpointService`

The application integrates with this SOAP service using **Apache Camel CXF**. The SOAP response is dynamically populated using data fetched from the Random User API when needed.

---

## Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---
