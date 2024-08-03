# eScooter Service Microservices Architecture Report

## Overview
This report details the architecture of an eScooter service project,
designed to allow users to locate, rent, and use eScooters in a city. 
The project employs a microservices architecture to ensure modularity, scalability, 
and maintainability. 
The core components include three main microservices and 
several auxiliary services for API management, service discovery, and monitoring.

## Microservices

### 1. User Service
- **Framework**: Spring Boot
- **Responsibilities**:
    - Manage user accounts.
    - Store and update user profiles.
    - Handle user-related queries and operations.

### 2. eScooter Service
- **Framework**: Micronaut
- **Responsibilities**:
    - Manage the inventory of eScooters.
    - Track eScooter availability and status.
    - Update eScooter locations.

### 3. Ride Service
- **Framework**: Spring Boot
- **Responsibilities**:
    - Handle ride creation, updates, and termination.
    - Provide information about ongoing rides.

## Auxiliary Services

### API Gateway
- **Framework**: Spring Cloud Gateway
- **Responsibilities**:
    - Route incoming requests to the appropriate microservices.
    - Provide a unified entry point for the client application.

### Service Discovery
- **Framework**: Eureka 
- **Responsibilities**:
    - Maintain a registry of microservices and their instances.
    - Enable dynamic service discovery for load balancing and fault tolerance.

### Monitoring and Observability
- **Components**:
    - **Prometheus**: Collects metrics from the microservices.
    - **Grafana**: Visualizes the metrics data collected by Prometheus.
    - **Elasticsearch, Logstash, Kibana (ELK Stack)**:
        - **Elasticsearch**: Stores logs for search and analysis.
        - **Logstash**: Aggregates and processes log data before indexing in Elasticsearch.
        - **Kibana**: Provides a user interface for log analysis and visualization.
### OpenAPI Specifications
The OpenAPI specifications for each microservice are available at the following URLs:

- User Service: http://localhost:8081/swagger-ui/index.html
- eScooter Service: http://localhost:8082/swagger-ui/index.html
- Ride Service: http://localhost:8083/swagger-ui/index.html

## Containerization


- **Docker**: All services are containerized using Docker to ensure consistent environments across 
different stages of development and deployment.
- **Docker Compose**: Used for defining and running multi-container Docker applications.
It simplifies the configuration of service dependencies and networks.


# Running the eScooter Service System

## Prerequisites
Ensure you have Docker and Docker Compose installed on your system.
You can download them from [Docker's official website](https://www.docker.com/).

## Steps to Run the System

1. **Clone the Repository**

   ```git clone https://github.com/MarcoFratta/Assignment5```

2. **Build the Services**: Run the following command to run the Docker images for the microservices and auxiliary services.
 
   ```docker-compose up```
3. **Access the Services**: The services will be available on their respective ports as defined in the docker-compose.yml file:

- *API Gateway*: http://localhost:8080
- *Eureka Server*: http://localhost:8761
- *Prometheus*: http://localhost:9090
- *Grafana*: http://localhost:3000
- *Kibana*: http://localhost:5601


