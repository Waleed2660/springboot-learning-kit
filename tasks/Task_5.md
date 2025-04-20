# Task 5 ~ Working with RabbitMQ

This task will walk you through the process of setting up RabbitMQ in a production ready enterprise application. After that,
we'll look at setting up new consumer & a producer for the RabbitMQ.

---

## ActiveMQ vs RabbitMQ

**Conceptual Difference**
- **ActiveMQ** follows a JMS-style model — queues for point-to-point, topics for pub-sub. Routing is simpler and abstracted.
- **RabbitMQ** uses Exchanges, which allow precise control over how messages are routed to queues.

Here's a simple diagram to illustrate the difference:

**ActiveMQ**
```mermaid
graph LR
    P[Producer] -->|send| Q[Queue]
    Q -->|consume| C[Consumer]
    
    subgraph "ActiveMQ Broker"
        Q
        DLQ
    end
    
    Q -.->|failed messages| DLQ
```

**RabbitMQ**

```mermaid
graph LR
    P[Producer] -->|publish| E[Exchange]
    E -->|route| Q[Queue]
    Q -->|consume| C[Consumer]
    
    Q -->|rejected/expired| DLX[Dead Letter Exchange]
    DLX -->|route| DLQ[Dead Letter Queue]

    subgraph "RabbitMQ Broker"
        E
        Q
        DLX
        DLQ
    end
```

### **Why Exchanges are used in RabbitMQ?**

- **Flexibility**: Exchanges allow you to define complex routing rules. You can have multiple queues bound to the same 
exchange with different routing keys.
- **Decoupling**: Producers and consumers can be decoupled. Producers send messages to exchanges without knowing about the queues.
- **Multiple Routing Options**: RabbitMQ supports different types of exchanges (direct, topic, fanout, headers) for various routing needs.
- **Load Balancing**: Multiple consumers can consume from the same queue, allowing for load balancing.

---

```mermaid
graph LR
    P[Order Service] -->|"routing_key=order.created"| E[Topic Exchange]
    E -->|"order.#"| Q1[Analytics Queue]
    E -->|"order.created"| Q2[Inventory Queue]
    E -->|"order.created"| Q3[Notification Queue]
```

## **RabbitMQ Setup**

In previous task, we went through ActiveMQ setup with Apache Camel. RabbitMQ can also be
configured using Apache Camel. But for RabbitMQ, it's been configured using Spring Boot.

### **Spring Boot Configuration**

