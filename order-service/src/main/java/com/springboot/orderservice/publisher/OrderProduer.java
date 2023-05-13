package com.springboot.orderservice.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.orderservice.dto.OrderEvent;

@Service
public class OrderProduer {

	private Logger LOGGER = LoggerFactory.getLogger(OrderProduer.class);

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.binding.routing.key}")
	private String orderRoutingKey;

	@Value("${rabbitmq.binding.routing2.key}")
	private String emailRoutingKey;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event sent to RabbitMQ => %s",
				orderEvent.toString()));
		rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);
		rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
	}
}
