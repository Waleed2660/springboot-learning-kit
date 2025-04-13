package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.CustomerAddress;
import com.springboot.learning.kit.dto.request.CustomerAddressRequest;
import com.springboot.learning.kit.repository.CustomerAddressRepository;
import com.springboot.learning.kit.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final OrderTransformer orderTransformer;
    private final CustomerAddressRepository customerAddressRepository;

    public long saveCustomerAddress(CustomerAddressRequest customerAddressRequest) {
        CustomerAddress customerAddress = orderTransformer.transformCustomerAddressToDomain(customerAddressRequest);
        return customerAddressRepository.save(customerAddress).getId();
    }
}
