package com.springboot.learning.kit.processor;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.service.AddressService;
import com.springboot.learning.kit.service.CustomerService;
import com.springboot.learning.kit.service.OrderItemService;
import com.springboot.learning.kit.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractOrderProcessor {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final OrderItemService orderItemService;

    /**
     * Checks if this processor can handle the given order type.
     *
     * @param orderType the type of order
     */
    public abstract boolean supports(OrderType orderType);

    /**
     * Processes the order.
     * @param order the order to process
     */
    public abstract void processOrder(OrderRequest order);

    /**
     * Saves the order to the database.
     * @Transactional ensures that the method is executed within a transaction.
     * @param orderRequest the order request object
     */
    @Transactional
    public void saveOrder(OrderRequest orderRequest) {
        log.error("Abstract Order Processor saving order: {}", orderRequest);

        // Save customer details and address
        long customerId = customerService.saveCustomerDetails(orderRequest.getCustomerDetails());
        long addressId = addressService.saveCustomerAddress(orderRequest.getCustomerAddress());

        // now save order as we've got customer and address IDs
        orderService.saveNewOrder(orderRequest, customerId, addressId);

        // now we can save order items
        orderItemService.saveOrderItems(orderRequest.getOrderItems(), orderRequest.getUUID());
    }
}
