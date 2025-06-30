# Spring Boot Learning Kit

## **Table of Contents**
- [Introduction](#introduction)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Pre-requisites](#pre-requisites)
  - [Docker](#docker)
  - [DBeaver](#dbeaver)
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

## **Tech Stack**
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Gradle**: Build automation tool.
- **ActiveMQ**: Message broker for asynchronous communication.
- **RabbitMQ**: Alternative message broker for asynchronous communication.
- **PostgreSQL**: Relational database for data storage.
- **Prometheus**: Monitoring and alerting toolkit.
- **Grafana**: Visualization tool for monitoring data.
- **Jmeter**: Java application performance monitoring tool.
- **Docker**: Containerization platform for local development.

---

## **Pre-requisites**

### IntelliJ IDEA 

To work on this project, you need to have IntelliJ IDEA installed. It is a powerful IDE for Java development that 
supports Spring Boot and Gradle. You can download the community version for free from
 the [JetBrains website](https://www.jetbrains.com/idea/download/other.html#).

### **Docker**

Install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop).

### **DBeaver**

DBeaver is a free, open-source database management tool that supports a wide range of databases, including MSSQL, MySQL,
PostgreSQL, Oracle, and more. It provides a user-friendly interface for managing and querying databases, making it a 
popular choice for developers and database administrators.

#### **Download DBeaver Client**:
- **MacOS**: Install via Homebrew:
```bash
  brew install --cask dbeaver-community
  ```
- **Official Website**: [Download DBeaver](https://dbeaver.io/download/)

---

## Getting Started

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