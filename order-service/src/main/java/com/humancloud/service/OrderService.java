package com.humancloud.service;

import com.humancloud.event.OrderCreatedEvent;
import com.humancloud.producer.OrderProducer;
import org.springframework.stereotype.Service;

@Service
public class OrderService 
{
    private final OrderProducer orderProducer;

    public OrderService(OrderProducer orderProducer) 
    {
        this.orderProducer = orderProducer;
    }

    public String createOrder(OrderCreatedEvent event) 
    {
        orderProducer.sendOrder(event);
        return "Order sent to Kafka successfully";
    }
}