# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.springboot.learning.kit.' is invalid and this project uses 'com.springboot.learning.kit' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin/packaging-oci-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.4.4/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers ActiveMQ Module Reference Guide](https://java.testcontainers.org/modules/activemq/)
* [Testcontainers MS SQL Server Module Reference Guide](https://java.testcontainers.org/modules/databases/mssqlserver/)
* [Prometheus](https://docs.spring.io/spring-boot/3.4.4/reference/actuator/metrics.html#actuator.metrics.export.prometheus)
* [Testcontainers](https://java.testcontainers.org/)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)
* [Docker Compose Support](https://docs.spring.io/spring-boot/3.4.4/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring for Apache ActiveMQ 5](https://docs.spring.io/spring-boot/3.4.4/reference/messaging/jms.html#messaging.jms.activemq)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Java Message Service API via Apache ActiveMQ Classic.](https://spring.io/guides/gs/messaging-jms/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* activemq: [`apache/activemq-classic:latest`](https://hub.docker.com/r/apache/activemq-classic)
* sqlserver: [`mcr.microsoft.com/mssql/server:latest`](https://mcr.microsoft.com/en-us/product/mssql/server/about/)

Please review the tags of the used images and set them to the same as you're running in production.

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.4.4/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`apache/activemq-classic:latest`](https://hub.docker.com/r/apache/activemq-classic)
* [`mcr.microsoft.com/mssql/server:latest`](https://mcr.microsoft.com/en-us/product/mssql/server/about/)

Please review the tags of the used images and set them to the same as you're running in production.

