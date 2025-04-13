package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.CustomerAddress;
import com.springboot.learning.kit.domain.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
