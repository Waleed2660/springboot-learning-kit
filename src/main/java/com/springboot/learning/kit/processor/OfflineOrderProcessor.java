package com.springboot.learning.kit.processor;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.service.AddressService;
import com.springboot.learning.kit.service.CustomerService;
import com.springboot.learning.kit.service.OrderItemService;
import com.springboot.learning.kit.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OfflineOrderProcessor extends AbstractOrderProcessor {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final OrderItemService orderItemService;

    public OfflineOrderProcessor(
            OrderService orderService,
            CustomerService customerService,
            AddressService addressService,
            OrderItemService orderItemService) {
        super(orderService, customerService, addressService, orderItemService);

        this.orderService = orderService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.orderItemService = orderItemService;
    }

    @Override
    public boolean supports(OrderType orderType) {
        return orderType.equals(OrderType.OFFLINE);
    }

    @Override
    public void processOrder(OrderRequest orderRequest) {
        log.info("Processing offline order: {}", orderRequest.getUUID());

        // Perform any specific processing for offline orders here

        // Call the saveOrder method from the parent class to save the order
        super.saveOrder(orderRequest);
    }
}
