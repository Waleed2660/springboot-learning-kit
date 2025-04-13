package com.springboot.learning.kit.transformer;

import com.springboot.learning.kit.domain.*;
import com.springboot.learning.kit.dto.request.CustomerAddressRequest;
import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.dto.request.OrderItemRequest;
import com.springboot.learning.kit.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderTransformer {

    /**
     * Converts an OrderRequest to an Order domain object.
     *
     * @param request the OrderRequest to convert
     * @return the Order domain object
     */
    public Order transformOrderRequestToDomain(OrderRequest request, long customerId, long addressId) {
        return Order.builder()
                .uuid(request.getUUID())
                .orderType(OrderType.valueOf(request.getOrderType()))
                .customerDetailsId(customerId)
                .customerAddressId(addressId)
                .totalAmount(request.getOrderAmount())
                .currency(request.getCurrency())
                .orderCreated(LocalDateTime.now())
                .build();
    }

    /**
     * Converts a CustomerDetailsRequest to a CustomerDetails domain object.
     *
     * @param detailsRequest the CustomerDetailsRequest to convert
     * @return the CustomerDetails domain object
     */
    public CustomerDetails transformCustomerDetailsToDomain(CustomerDetailsRequest detailsRequest) {
        return CustomerDetails.builder()
                .name(detailsRequest.getName())
                .email(detailsRequest.getEmail())
                .phone(detailsRequest.getPhone())
                .build();
    }

    /**
     * Converts a CustomerAddressRequest to a CustomerAddress domain object.
     *
     * @param addressRequest the CustomerAddressRequest to convert
     * @return the CustomerAddress domain object
     */
    public CustomerAddress transformCustomerAddressToDomain(CustomerAddressRequest addressRequest) {
        return CustomerAddress.builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .zipCode(addressRequest.getZipCode())
                .country(addressRequest.getCountry())
                .build();
    }

    /**
     * Converts a list of OrderItemRequest to a list of OrderItem domain objects.
     *
     * @param request the list of OrderItemRequest to convert
     * @return the list of OrderItem domain objects
     */
    public List<OrderItem> transformOrderItemToDomain(List<OrderItemRequest> request, long orderUUID) {
        return request.stream()
                .map(itemRequest -> OrderItem.builder()
                        .orderId(orderUUID)
                        .productId(itemRequest.getProductId())
                        .quantity(itemRequest.getQuantity())
                        .pricePerUnit(itemRequest.getPricePerUnit())
                        .build())
                .toList();
    }

}
