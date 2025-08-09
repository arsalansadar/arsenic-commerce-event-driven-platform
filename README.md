# arsenic-commerce-event-driven-platform

**Arsenic Commerce Event-Driven Platform** is a modern microservices-based system designed for scalable and resilient e-commerce operations.  
Built using **Spring Boot**, **Spring Cloud**, **MongoDB**, and **RabbitMQ**, this platform demonstrates a cloud-native architecture leveraging asynchronous messaging and service discovery.

## Key Features
- **Authentication Service**: User registration, login with JWT, OTP verification for secure access.
- **API Gateway**: Centralized routing and security enforcement for all microservices.
- **Eureka Server**: Service registry enabling dynamic discovery and load balancing.
- **Order Service**: Handles order creation, stores data in MongoDB Atlas, and publishes order events.
- **Notification Service**: Consumes order events from RabbitMQ to send email and SMS notifications asynchronously.

This repository provides the full source code and configurations to deploy a production-ready event-driven commerce platform.

---

## Getting Started

Instructions on how to set up, run, and test each microservice independently can be found in their respective directories.

---

## Technologies Used

- Java 8+ / Spring Boot 3.x  
- Spring Cloud Netflix Eureka & Gateway  
- MongoDB Atlas  
- RabbitMQ  
- JWT Authentication  

---

Feel free to contribute, raise issues, or suggest enhancements!

---

