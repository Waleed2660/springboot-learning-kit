package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {}
