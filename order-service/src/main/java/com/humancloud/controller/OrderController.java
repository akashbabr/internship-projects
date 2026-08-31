package com.humancloud.controller;

import com.humancloud.event.OrderCreatedEvent;

import com.humancloud.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController 
{
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderCreatedEvent event) 
    {
        return orderService.createOrder(event);
    }
}