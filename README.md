# Spring Boot Learning Kit

[![oosmetrics](https://api.oosmetrics.com/api/v1/badge/achievement/6b9da633-20de-41db-b743-4c55ee36e80f.svg)](https://oosmetrics.com/repo/Waleed2660/springboot-learning-kit)

## **Table of Contents**
- [Introduction](#introduction)
- [What You Will Learn](#what-you-will-learn)
- [Tasks](#tasks)
- [Getting Started](#getting-started)
- [API Collection](#api-collection)
- [License](#license)

---

## **Introduction**

This project serves as a learning kit for Spring Boot, focusing on the integration of various technologies such as 
ActiveMQ, RabbitMQ, and PostgreSQL. It provides a hands-on experience for developers looking to enhance their skills 
in building modern applications. The aim of this project is to:

- Provide a practical understanding of Spring Boot and its ecosystem.
- Tackle real-world challenges in application development.
- Explore the integration of messaging brokers like ActiveMQ and RabbitMQ.
- Follow best practices in software development.
- Encourage collaboration and knowledge sharing among developers.

---

## **What You Will Learn**

Working through the tasks in this project will give you practical experience with the following:

- **Spring Boot**: REST API design, request validation, custom exceptions, profiles, and application configuration
- **Spring Data JPA + PostgreSQL**: entities, repositories, custom queries, and avoiding common pitfalls like N+1 writes
- **Apache ActiveMQ + Apache Camel**: point-to-point queues, virtual topics, dead letter queues, and centralized route configuration
- **RabbitMQ**: exchanges, bindings, dead letter exchanges, and message acknowledgement
- **Liquibase**: database schema migrations, rollback scripts, and changelog management
- **JUnit 5 + Mockito + TestContainers**: unit tests, integration tests, snapshot testing, and running tests against real Docker containers
- **Prometheus + Micrometer**: exposing application metrics, custom `@Timed` annotations, and PromQL queries
- **Grafana**: building dashboards, visualising HTTP and database metrics, and importing community dashboards
- **Apache JMeter**: load testing, interpreting throughput and percentile results, and correlating load with Grafana dashboards
- **Spotless + Palantir Java Format**: enforcing a consistent code style automatically as part of the build
- **Docker + Docker Compose**: running and wiring together multiple services locally

---

## **Tasks**

Each task builds on the previous one and covers a specific area of the application. Navigate to the [tasks](tasks) folder to get started.

- **Task 01 - Project Setup**: spin up the database and messaging brokers, run the application, and verify everything is healthy
- **Task 02 - Kicking off Development**: implement request validators, custom exceptions, and a new order status API
- **Task 03 - Debug & Fix Critical Bug**: investigate a duplicate-insert bug, understand `EntityManager.persist()` vs `save()`, and fix transactional boundaries
- **Task 04 - Apache ActiveMQ with Apache Camel**: configure Camel routes, consume from a queue, handle DLQs, and produce events to a Virtual Topic
- **Task 05 - RabbitMQ with Spring Boot**: configure exchanges and bindings, fix an infinite redelivery bug, and publish messages to a topic exchange
- **Task 06 - Database Schema Migration & Optimisation**: add a new table with Liquibase, generate and execute rollback SQL, and fix an N+1 database write
- **Task 07 - Unit and Integration Tests**: write unit tests with Mockito, snapshot tests, and integration tests using TestContainers
- **Task 08 - Code Style and Formatting**: configure Spotless with Palantir Java Format and automate formatting as part of the Gradle build
- **Task 09 - Prometheus Metrics Integration**: expose application metrics via Spring Actuator and configure Prometheus scraping
- **Task 10 - Grafana Integration**: connect Grafana to Prometheus, build dashboards, and add `@Timed` annotations for database operations
- **Task 11 - Load Testing**: run load tests with Apache JMeter, interpret results, and observe the impact on Grafana dashboards
- **Task 12 - Global Exception Handling**: replace per-controller try-catch blocks with `@ControllerAdvice` and adopt RFC 7807 Problem Details

---

## **Getting Started**

1. **Fork this repository** to your own GitHub account
2. 📥 Clone your forked repository to your local machine:
 ```bash
   git clone https://github.com/YOUR_USERNAME/springboot-learning-kit.git
```
3. Create a new branch:
```bash
   git checkout -b your-feature-branch
```
4. Navigate to [Tasks](tasks) folder and kick off the work.

---

## **API Collection**
This project includes a **Bruno Collection** that contains all the APIs for easy testing and exploration.

### **Accessing the Bruno Collection**
- The collection is designed to work with the APIs exposed by this project.
- It includes endpoints for health checks and other application functionalities.

### **How to Use**
1. Download and install the [Bruno API Client](https://www.usebruno.com/).
2. Import the provided Bruno collection from [Docs Directory](docs/SpringBoot%20Learning%20Kit%20-%20APIs) into the client.
3. Use the pre-configured endpoints to test the APIs.

### **Available Endpoints**
- **Health Check**: [http://localhost:8080/OrderService/management/healthcheck](http://localhost:8080/OrderService/management/healthcheck)  
  Response: 
  `ActiveMQ: OK
  RabbitMQ: OK
  Database: OK`

_Make sure the application and required services are running before testing the APIs._

---

## **Contributing**

Contributions to this project are welcomed! If you have ideas, improvements, or bug fixes, please do raise an issue or submit a pull request.

---

## **License**

This project is licensed under the [CC BY-NC 4.0 License](https://creativecommons.org/licenses/by-nc/4.0/).  
You may use, modify, and share it non-commercially with attribution.