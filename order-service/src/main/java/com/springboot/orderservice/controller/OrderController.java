package com.springboot.orderservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.orderservice.dto.Order;
import com.springboot.orderservice.dto.OrderEvent;
import com.springboot.orderservice.publisher.OrderProduer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderProduer orderProduer;

	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent event = new OrderEvent();
		event.setStatus("PENDING");
		event.setMessage("order is in pending status");
		event.setOrder(order);
		orderProduer.sendMessage(event);
		return "Order sent to the RabbitMQ...";
	}
}
