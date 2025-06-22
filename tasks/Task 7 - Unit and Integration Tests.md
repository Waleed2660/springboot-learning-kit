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
- **TestContainers**: A library that allows you to run Docker containers for integration tests, ensuring a consistent
environment.
- **Snapshot Testing**: A technique for capturing the output of a component and comparing it against a stored snapshot to 
ensure consistency.
- **AssertJ**: A fluent assertion library that provides a rich set of assertions for testing.

---

## Test Folder Structure

I've already added some basic unit & integration tests, the TestContainers are also configured in [TestContainersConfig.java](../src/test/java/com/springboot/learning/kit/config/TestContainersConfig.java) class.
Our test structure follows a clear separation between unit tests, integration tests, and shared test configurations:

```
src/
└── test/
    ├── java/
    │   └── com/springboot/learning/kit/
    │       ├── config/               # Shared test configurations
    │       │   └── TestContainersConfig.java
    │       ├── integration/          # Integration tests
    │       │   └── HealthCheckIT.java
    │       └── unit/                # Unit tests
    │           └── SampleUnitTest.java
    └── resources/
        └── application-test.properties   # Test-specific properties
```

---

## Unit Tests

Unit tests focus on verifying individual components of the application—such as services, controllers, or utility 
classes—in complete isolation from their dependencies. By using mocking frameworks like Mockito, unit tests replace 
real dependencies with mock objects, ensuring that only the logic within the component under test is exercised. This 
approach helps quickly identify logic errors, enforces correct behavior at the smallest level, and provides fast 
feedback during development.

**Unit tests are essential for:**

- Validating business logic in isolation
- Catching regressions early
- Enabling safe refactoring
- Ensuring high code quality

### Time to write a Unit Test

**Creating Test Class:**
1. Create a new package named `service` under `src/test/java/com/springboot/learning/kit/unit`.
2. Create a new class named `OrderServiceTest.java` under the new package.
3. We'll write a unit test for the `saveNewOrder()` method in the `OrderService` class.
4. You can use `SampleUnitTest.java` as a reference but we'll need to add a lot more stuff for this one.

**Injecting Dependencies:**
1. Navigate to [OrderService.java](../src/main/java/com/springboot/learning/kit/service/OrderService.java).
2. You'll see that the `saveNewOrder()` is using instances of `OrderTransformer` and `EntityManager` to perform its 
   operations.
3. To test `OrderService`, we need to mock these dependencies using Mockito.
4. Add the following annotations to your test class:
   ```java
    @ExtendWith(MockitoExtension.class) // Enables Mockito annotations
    ```
5. Use `@Mock` to create mock instances of `OrderTransformer` and `EntityManager`, and use `@InjectMocks` 
   to inject these mocked instances into the `OrderService` instance.
6. Your class should look something like this:
    ```java
    @ExtendWith(MockitoExtension.class)
    class OrderServiceTest {
    
        @Mock
        private OrderTransformer orderTransformer;
        
        @Mock
        private EntityManager entityManager;
        
        @InjectMocks
        private OrderService orderService;
        
        @Test
        void saveNewOrder_success() {
            
        }
    }
    ```
   
**Writing the Test Method:**
1. Inside the `testSaveNewOrder()` method, we will:
   - Create a mock `OrderRequest` object.
   - Mock the behavior of `orderTransformer.transformOrderRequestToDomain()` to return a new `Order` entity.
   - Mock the behavior of `entityManager.persist()` to do nothing (void method).
   - Call the `saveNewOrder()` method on the `orderService`.
   - Verify that the `orderTransformer.toEntity()` and `entityManager.persist()` methods were called with the correct 
     parameters.
2. You're encouraged to write the test method yourself, but here's a reference implementation:
   
    ⚠️ **Note:** You do not need to use `public` modifier for test classes & methods when using JUnit 5.
    ```java
    @Test
    void saveNewOrder_success() {
        long customerId = 1L;
        long addressId = 2L;
        OrderRequest orderRequest = new OrderRequest();
        Order order = new Order();
        
        // when
        when(orderTransformer.transformOrderRequestToDomain(orderRequest)).thenReturn(order);
        
        // Act
        orderService.saveNewOrder(orderRequest);
        
        // Assert
        verify(orderTransformer).transformOrderRequestToDomain(orderRequest);
        verify(entityManager).persist(order);
    }
    ```
3. You can run the test using IntelliJ by right-clicking on the test class & hit `🟢 run` button

![img.png](resources/task7_runTestClassIntelliJ.png)

---

### 📸 Let's write a different kind of Unit Test
In addition to the standard unit tests, we can also implement snapshot tests. Snapshot tests capture the output of a component
and compare it against a stored snapshot to ensure consistency. This is particularly useful for testing complex objects or
data structures where the output can be large or complex.

### How Snapshot Testing Works

I'll try to explain how snapshot testing works using a simple flowchart.

```mermaid
graph TD
    subgraph Second Run
      A2[Test runs again] --> E[Snapshot file exists]
      E --> F[Compare current output with stored snapshot]
      F -- Match --> G[Test passes]
      F -- Mismatch --> H[Test fails and shows diff]
    end
    subgraph First Run
       A[Test runs for the first time] --> B[No snapshot exists]
       B --> C[Create snapshot file with current output]
       C --> D[Test passes]
    end
```


### Snapshot Configuration

1. Firstly, we need to import new dependencies in our `build.gradle` to enable snapshot testing:
   ```groovy
   testImplementation 'io.github.origin-energy:java-snapshot-testing-junit5:4.0.8'
   testImplementation 'io.github.origin-energy:java-snapshot-testing-plugin-jackson:4.0.8'
   ```
   Create a new `snapshot.properties` file in the `src/test/resources` directory and add the following content. These properties
   will configure the snapshot testing framework. I've copied this from the [origin-energy:java-snapshots](https://github.com/origin-energy/java-snapshot-testing/blob/master/README.md#:~:text=/src/test/resources/snapshot.properties)
   ```properties
   serializer=au.com.origin.snapshots.serializers.v1.ToStringSnapshotSerializer
   serializer.base64=au.com.origin.snapshots.serializers.v1.Base64SnapshotSerializer
   serializer.json=au.com.origin.snapshots.jackson.serializers.v1.JacksonSnapshotSerializer
   serializer.orderedJson=au.com.origin.snapshots.jackson.serializers.v1.DeterministicJacksonSnapshotSerializer
   comparator=au.com.origin.snapshots.comparators.v1.PlainTextEqualsComparator
   reporters=au.com.origin.snapshots.reporters.v1.PlainTextSnapshotReporter
   snapshot-dir=__snapshots__
   output-dir=src/test/java
   ci-env-var=CI
   update-snapshot=none
   ```
2. Make sure to refresh your Gradle project to download the new dependencies.
3. Now create a new class named `OrderStatusServiceTest.java` under the `unit/service` package.
4. In this test, we'll pass an Order Id to the `OrderStatusService` and verify that the returned 
   `OrderStatusResponse` matches a predefined snapshot.
5. Use the `@ExtendWith({MockitoExtension.class, SnapshotExtension.class})` annotation to enable mockito + snapshot testing for
this class.
6. Inject the `OrderStatusService` using `@InjectMocks`.
7. You'd also need to mock the `OrderRepository` & `OrderItemRepository` dependency using `@Mock`.

**You're encouraged to go ahead & write the test method yourself & see if you can get it to work, but here's a reference implementation:**

```java
@ExtendWith({MockitoExtension.class, SnapshotExtension.class})
class OrderStatusServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderStatusService orderStatusService;

    private Expect expect;

    @Test
    void verifyOrderStatusResponse() {
        // Arrange
        long orderUUID = 1L;
        
        // Mock the repository method that fetches the order
        when(orderRepository.findById(orderUUID)).thenReturn(Optional.of(createOrder(orderUUID)));

        // Mock the repository method that fetches the order items
        when(orderItemRepository.findByOrderId(orderUUID)).thenReturn(createOrderItems(orderUUID));

        // Act
        OrderStatusResponse status = orderStatusService.getOrderStatus(orderUUID);
        
        // Assert
        expect.serializer("json").toMatchSnapshot(status);
    }

    /**
     * Creates an order with the given order ID.
     * @param orderId
     * @return Order
     */
    private @NotNull Order createOrder(long orderId) {
        return Order.builder()
                .uuid(orderId)
                .orderType(OrderType.ONLINE)
                .customerDetailsId(101L)
                .customerAddressId(201L)
                .totalAmount(BigDecimal.valueOf(99.99))
                .currency("GBP")
                .orderCreated(java.time.LocalDateTime.now())
                .build();
    }

    /**
     * Creates a list of order items for the given order ID.
     * @param orderId
     * @return List<OrderItem>
     */
    private @NotNull List<OrderItem> createOrderItems(long orderId) {
        return List.of(
                OrderItem.builder()
                        .orderId(orderId)
                        .productId(101L)
                        .quantity(2)
                        .status("CONFIRMED")
                        .pricePerUnit(BigDecimal.valueOf(49.99))
                        .build(),
                OrderItem.builder()
                        .orderId(orderId)
                        .productId(102L)
                        .quantity(1)
                        .status("PENDING")
                        .pricePerUnit(BigDecimal.valueOf(10.50))
                        .build()
        );
    }
}
```

**🚀 Time to Run the Test**

When you run the test for the first time, it will create a snapshot file in the `unit/service/__snapshots__` directory.
It will contain the serialized output of the `OrderStatusResponse` object. The test should pass.

![img.png](resources/task7_showing_snapshot.png)

This is the snapshot file content:
```json
com.springboot.learning.kit.unit.service.OrderStatusServiceTest.verifyOrderStatusResponse=[
  {
    "orderId": 1,
    "orderType": "ONLINE",
    "items": [
      {
        "productId": 101,
        "quantity": 2,
        "status": "CONFIRMED"
      },
      {
        "productId": 102,
        "quantity": 1,
        "status": "PENDING"
      }
    ]
  }
]
```

Now if you run the test again, it will compare the current output with the stored snapshot. If they match, the test passes;
if they differ, the test fails, indicating that the output has changed. This snapshot should be committed to your repository 
to ensure that the expected output is versioned alongside your code.

---

## Integration Tests

Integration tests verify how different parts of the application work together in a real or production-like environment. 
While unit tests focus on isolated components, integration tests ensure that multiple components (controllers, services, 
repositories, and external systems) interact correctly as a whole.

**Pre-Requisites for Integration Tests**

Make sure that Docker is running on your system as when it comes to integration tests, we need to ensure that the application context is loaded correctly, and all
dependent components are available such as databases, message brokers, etc.



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

### Our Integration Test Setup

Navigate to the [TestContainersConfig.java](../src/test/java/com/springboot/learning/kit/config/TestContainersConfig.java) 
class to see how the TestContainers are configured. You'll see that we are using test containers to spin up a PostgreSQL,
RabbitMQ, and ActiveMQ instance for our integration tests.

**Note:** All Integration test classses should be annotated with `@SpringBootTest` to load the full application context & 
import the `TestContainersConfig` class to ensure that the test containers are started before the tests run.
```java
@SpringBootTest
@Import(TestContainersConfig.class)
```

---






## **Conclusion**



