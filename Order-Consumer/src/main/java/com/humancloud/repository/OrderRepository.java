package com.humancloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humancloud.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> 
{
	 List<Order> findByOrderIdIn(List<Long> orderIds);
}