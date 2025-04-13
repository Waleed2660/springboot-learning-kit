# Spring Boot Learning Kit

A Spring Boot application designed for learning and experimentation with modern technologies like ActiveMQ, RabbitMQ & Postgres.

---

## **Tech Stack**
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Gradle**: Build automation tool.
- **ActiveMQ**: Message broker for asynchronous communication.
- **RabbitMQ**: Alternative message broker for asynchronous communication.
- **PostgreSQL**: Relational database for data storage.
- **Docker**: Containerization platform for local development.

---

## **ActiveMQ**
ActiveMQ is used as the message broker in this project.

### **Accessing ActiveMQ**
- **Broker Port**: `61616`
- **Web Console Port**: `8161`
- **Web Console URL**: [http://localhost:8161](http://localhost:8161)
- **Default Credentials**:
    - Username: `admin`
    - Password: `admin`

---

## **RabbitMQ**
RabbitMQ is an alternative message broker that can be used in this project.
### **Accessing RabbitMQ**
- **Broker Port**: `5672`
- **Web Console Port**: `15672`
- **Web Console URL**: [http://localhost:15672](http://localhost:15672)
- - **Default Credentials**:
  - Username: `user`
  - Password: `password`

---

## **PostgreSQL**
PostgreSQL is used as the relational database in this project.

### **Database Configuration**
- DB_NAME: `Order_Service`
- DB_HOST: `localhost`
- DB_PORT: `5432`
- DB_USER: `user`
- DB_PASSWORD: `password`

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

## **Local Development**
This project uses Docker Compose to simplify the setup of dependent services.

### **Starting All Services**
To start both ActiveMQ and MSSQL, run:
```bash
   docker-compose up -d
```

### **Stopping All Services**
To stop all running services, use:
```bash
   docker-compose down
```

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
- **Health Check**: [http://localhost:8080/management/healthcheck](http://localhost:8080/management/healthcheck)  
  Response: `OK`

Make sure the application and required services (ActiveMQ and MSSQL) are running before testing the APIs.

---

## **Health Check**
The application provides a health check endpoint to verify its status:
- **URL**: [http://localhost:8080/management/healthcheck](http://localhost:8080/management/healthcheck)
- **Response**: `OK`

---

## **License**

This project is licensed under the [CC BY-NC 4.0 License](https://creativecommons.org/licenses/by-nc/4.0/).  
You may use, modify, and share it non-commercially with attribution.