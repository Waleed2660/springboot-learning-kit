package com.springboot.learning.kit.integration.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.learning.kit.config.BaseIntegrationTest;
import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.dto.request.CustomerAddressRequest;
import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.dto.request.OrderItemRequest;
import com.springboot.learning.kit.dto.request.OrderRequest;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class OrderControllerTest extends BaseIntegrationTest {

    @Test
    void testOrderCreation() {
        String orderCreationUrl = getBaseUrl() + "/order/submit";

        // Created a payload for order creation
        OrderRequest orderRequest = createOrderPayload();

        // Sending POST request to create an order
        ResponseEntity<String> response = restTemplate.postForEntity(orderCreationUrl, orderRequest, String.class);

        // Querying the Database to verify the order was created
        Order orderFromDB = entityManager
                .createQuery("SELECT o FROM Order o WHERE o.uuid = :uuid", Order.class)
                .setParameter("uuid", orderRequest.getUUID())
                .getSingleResult();

        // Asserting the API Response and Database state
        assertAll(
                () -> assertEquals(200, response.getStatusCode().value(), "Response status should be 200 OK"),
                () -> assertNotNull(response.getBody(), "Response body should not be null"),
                () -> assertTrue(
                        response.getBody().contains("Order submitted successfully"),
                        "Response body should contain success message"),
                () -> assertNotNull(orderFromDB, "Order should be present in the database"),
                () -> assertEquals(orderRequest.getUUID(), orderFromDB.getUuid(), "Order UUID should match"),
                () -> assertEquals(
                        orderRequest.getOrderType(), orderFromDB.getOrderType().toString(), "Order type should match"));
    }

    private OrderRequest createOrderPayload() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUUID(1234567L);
        orderRequest.setOrderType("ONLINE");

        CustomerDetailsRequest customerDetails = new CustomerDetailsRequest();
        customerDetails.setName("John Doe");
        customerDetails.setEmail("john.doe@gmail.com");
        customerDetails.setPhone("+1234567890");
        orderRequest.setCustomerDetails(customerDetails);

        CustomerAddressRequest customerAddress = new CustomerAddressRequest();
        customerAddress.setStreet("123 Main St");
        customerAddress.setCity("New York");
        customerAddress.setState("NY");
        customerAddress.setZipCode("10001");
        customerAddress.setCountry("USA");
        orderRequest.setCustomerAddress(customerAddress);

        OrderItemRequest item1 = new OrderItemRequest();
        item1.setProductId(1L);
        item1.setQuantity(2);
        item1.setPricePerUnit(BigDecimal.valueOf(29.99));

        OrderItemRequest item2 = new OrderItemRequest();
        item2.setProductId(2L);
        item2.setQuantity(1);
        item2.setPricePerUnit(BigDecimal.valueOf(49.99));

        orderRequest.setOrderItems(List.of(item1, item2));
        orderRequest.setOrderAmount(BigDecimal.valueOf(item1.getPricePerUnit().doubleValue() * item1.getQuantity()
                + item2.getPricePerUnit().doubleValue() * item2.getQuantity()));
        orderRequest.setCurrency("USD");
        return orderRequest;
    }
}
