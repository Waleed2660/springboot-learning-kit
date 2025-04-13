package com.springboot.learning.kit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerAddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
