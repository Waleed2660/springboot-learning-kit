package com.springboot.learning.kit.routes;

import com.springboot.learning.kit.consumer.NewOrderConsumer;
import lombok.RequiredArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ActiveMQRoutes extends RouteBuilder {

    private static final int MAX_RETRIES = 1;

    @Value("${amq.order.placement.queue}")
    private String orderPlacementQueue;

    @Value("${amq.order.placement.queue.dlq}")
    private String orderPlacementQueueDlq;

    @Value("${amq.order.status.queue}")
    private String orderStatusQueue;

    @Value("${amq.order.status.queue.dlq}")
    private String orderStatusQueueDlq;

    @Value("${amq.order.cancellation.queue}")
    private String orderCancellationQueue;

    @Value("${amq.order.cancellation.queue.dlq}")
    private String orderCancellationQueueDlq;

    private final NewOrderConsumer newOrderConsumer;

    @Override
    public void configure() throws Exception {

        // Order placement route
        from("activemq:queue:" + orderPlacementQueue)
                .routeId("orderPlacementRoute")
                .log(LoggingLevel.INFO, "Processing new order: ${body}")
                .onException(Exception.class)
                .maximumRedeliveries(MAX_RETRIES)
                .to("activemq:queue:" + orderPlacementQueueDlq)
                .log(LoggingLevel.ERROR, "Order processing failed: ${exception.message}")
                .handled(true)
                .end()
                .bean(newOrderConsumer, "processActiveMQOrder")
                .log(LoggingLevel.INFO, "Order processed successfully");
    }
}
