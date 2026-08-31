/*
package com.humancloud.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.humancloud.entity.Order;
import com.humancloud.event.OrderCreatedEvent;
import com.humancloud.repository.OrderRepository;

@Service
public class OrderConsumer {

	private final OrderRepository orderRepository;

	public OrderConsumer(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@RetryableTopic(
			attempts = "4",
			backoff = @Backoff(delay = 1000)
	)
	@KafkaListener(
			topics = "order-created",
			groupId = "order-consumer-group",
	containerFactory = "kafkaListenerContainerFactory"
	)
	public void consumeOrder(OrderCreatedEvent event) {
		try
		{
        	Thread.sleep(5000);
        }
		catch (InterruptedException e)
		{
            throw new RuntimeException(e);
        }

		System.out.println("Processing Order: " + event.getOrderId());

		if ("FAIL".equalsIgnoreCase(event.getProduct())) {
			throw new RuntimeException("Simulated database failure");
		}

		Order order = new Order();

		order.setOrderId(event.getOrderId());
		order.setCustomerName(event.getCustomerName());
		order.setProduct(event.getProduct());
		order.setAmount(event.getAmount());

		orderRepository.save(order);

		System.out.println("Order saved to MySQL: " + event.getOrderId());
	}
}



*/

package com.humancloud.consumer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.humancloud.entity.Order;
import com.humancloud.event.OrderCreatedEvent;
import com.humancloud.repository.OrderRepository;

@Service
public class OrderConsumer {

	private final OrderRepository orderRepository;

	public OrderConsumer(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@KafkaListener(topics = "order-created", groupId = "order-consumer-group")
	public void consumeOrder(List<OrderCreatedEvent> events) {

		System.out.println("Received batch size: " + events.size());

		// 1. Collect all order IDs from Kafka batch
		List<Long> orderIds = events.stream().map(OrderCreatedEvent::getOrderId).toList();

		// 2. One DB query to find already existing orders
		List<Order> existingOrders = orderRepository.findByOrderIdIn(orderIds);

		// 3. Put existing IDs into a Set
		Set<Long> existingOrderIds = new HashSet<>();

		for (Order order : existingOrders) 
		{
			existingOrderIds.add(order.getOrderId());
		}

		List<Order> newOrders = new ArrayList<>();

		for (OrderCreatedEvent event : events) 
		{
			System.out.println("Processing Order: " + event.getOrderId());

			if ("FAIL".equalsIgnoreCase(event.getProduct()))
			{
				throw new RuntimeException("Simulated database failure");
			}

			if (existingOrderIds.contains(event.getOrderId())) {

				System.out.println("Order already exists. Skipping: " + event.getOrderId());

				continue;
			}

			Order order = new Order();

			order.setOrderId(event.getOrderId());
			order.setCustomerName(event.getCustomerName());
			order.setProduct(event.getProduct());
			order.setAmount(event.getAmount());

			newOrders.add(order);
		}

		// 5. Save only new orders
		if (!newOrders.isEmpty()) {

			orderRepository.saveAll(newOrders);

			System.out.println("New orders saved to MySQL: " + newOrders.size());

		} else {

			System.out.println("No new orders to save.");
		}
	}
}
