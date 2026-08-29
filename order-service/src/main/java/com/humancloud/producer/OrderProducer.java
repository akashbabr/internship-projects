package com.humancloud.producer;

import com.humancloud.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer 
{
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "order-created";

    public OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) 
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderCreatedEvent event) 
    {
        kafkaTemplate.send(TOPIC, event.getOrderId().toString(), event);
    }
}