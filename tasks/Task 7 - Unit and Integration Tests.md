# Task 7 ~ Unit and Integration Tests

Unit and integration tests are fundamental pillars for building production-ready applications. 
Unit tests verify individual components in isolation, ensuring each service, controller, or utility class functions 
correctly at the most granular level. Integration tests, on the other hand, validate how different components interact 
within the Spring context, including database operations, REST endpoints, and message queues. In a production 
environment, where downtime can lead to significant business impact

In this task, we'll work with following tools & techniques:

- **JUnit**: A widely used testing framework for Java applications.
- **Mockito**: A mocking framework that allows you to create mock objects for testing.
- **Spring Test**: Provides support for testing Spring components, including loading the application context and 
injecting dependencies.
- **Testcontainers**: A library that allows you to run Docker containers for integration tests, ensuring a consistent
environment.
- **WebMVC Test**: A Spring module that provides support for testing web applications, including REST controllers.
- **Snapshot Testing**: A technique for capturing the output of a component and comparing it against a stored snapshot to 
ensure consistency.
- **AssertJ**: A fluent assertion library that provides a rich set of assertions for testing.

---

## Unit Tests



---

## Integration Tests

Integration tests verify how different parts of the application work together in a real or production-like environment. 
While unit tests focus on isolated components, integration tests ensure that multiple components (controllers, services, 
repositories, and external systems) interact correctly as a whole. 

Using tools like Testcontainers, integration tests run with actual dependencies (databases, message brokers) inside 
Docker containers, providing a consistent and reproducible test environment. This approach helps identify:
- Configuration issues
- Data flow problems
- External system integration failures
- Transaction management concerns

By testing with real dependencies instead of mocks, integration tests provide higher confidence that the application 
will function correctly in production.


### Integration Test Request Flow
This diagram illustrates how integration tests interact with the Spring application context and real dependencies
(like databases or message brokers) provisioned by Testcontainers during test execution.
```mermaid
graph LR
    IT[Integration Test] --> C[Controller]
    C --> S[Service]
    S --> R[Repository]
    R --> DB[(PostgreSQL \nTest Container)]
    
    subgraph Application
        C
        S
        R
    end
    subgraph Docker
        DB
    end
    style DB stroke:#333,stroke-width:2px
```





---

## **Bonus Task**



---


## **Conclusion**



---
