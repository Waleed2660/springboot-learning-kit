# Spring Boot Learning Kit

A Spring Boot application designed for learning and experimentation with modern technologies like ActiveMQ and MSSQL. This project demonstrates the integration of messaging and database services using Docker for local development.

---

## **Tech Stack**
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Gradle**: Build automation tool.
- **ActiveMQ**: Message broker for asynchronous communication.
- **MSSQL**: Relational database for data storage.
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

## **MSSQL**
MSSQL is used as the relational database for this project.

### **Database Configuration**
- **Port**: `1433`
- **Environment Variables**:
    - `ACCEPT_EULA`: `yes`
    - `MSSQL_SA_PASSWORD`: `verYs3cret`

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
2. Import the provided Bruno collection from [Docs Directory](./Docs/SpringBoot%20Learning%20Kit%20-%20APIs) into the client.
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
This project is licensed under the MIT License.