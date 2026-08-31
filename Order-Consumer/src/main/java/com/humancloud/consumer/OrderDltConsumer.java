/*
package com.humancloud.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.humancloud.event.OrderCreatedEvent;

@Service
public class OrderDltConsumer 
{
	@KafkaListener(topics = "order-created.DLT", groupId = "order-dlt-group")
	public void consumeDlt(OrderCreatedEvent event) 
	{
		System.out.println("DLT RECEIVED - Order ID: " + event.getOrderId());
		System.out.println("DLT RECEIVED - Customer: " + event.getCustomerName());
		System.out.println("DLT RECEIVED - Product: " + event.getProduct());
	}
}*/
