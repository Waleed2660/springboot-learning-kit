package com.springboot.learning.kit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {
    private Long UUID;
    private String orderType;
    private CustomerDetailsRequest customerDetails;
    private CustomerAddressRequest customerAddress;
    private List<OrderItemRequest> orderItems;
    private BigDecimal orderAmount;
    private String currency;
}
