package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.CustomerAddress;
import com.springboot.learning.kit.dto.request.CustomerAddressRequest;
import com.springboot.learning.kit.repository.CustomerAddressRepository;
import com.springboot.learning.kit.transformer.OrderTransformer;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final OrderTransformer orderTransformer;
    private final CustomerAddressRepository customerAddressRepository;

    @Timed(value = "database.write.operations", description = "Time taken to save customer address to database")
    public long saveCustomerAddress(CustomerAddressRequest customerAddressRequest) {
        CustomerAddress customerAddress = orderTransformer.transformCustomerAddressToDomain(customerAddressRequest);
        return customerAddressRepository.save(customerAddress).getId();
    }
}
