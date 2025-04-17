package com.springboot.learning.kit.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderStatusResponse {
    private Long orderId;
    private String orderStatus;
    private String orderType;
    private LocalDateTime orderCreated;
    private List<OrderItemStatusResponse> items;
}
