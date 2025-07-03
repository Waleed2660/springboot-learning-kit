package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.CustomerDetails;
import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.repository.CustomerDetailsRepository;
import com.springboot.learning.kit.transformer.OrderTransformer;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final OrderTransformer orderTransformer;
    private final CustomerDetailsRepository customerDetailsRepository;

    /**
     * This method saves customer details to the database.
     *
     * @param customerDetailsRequest the customer details request object
     * @return the ID of the saved customer details
     */
    @Timed(value = "save.new.customer", description = "Time taken to save customer details to database")
    public long saveCustomerDetails(CustomerDetailsRequest customerDetailsRequest) {
        CustomerDetails customerDetails = orderTransformer.transformCustomerDetailsToDomain(customerDetailsRequest);
        return customerDetailsRepository.save(customerDetails).getId();
    }
}
