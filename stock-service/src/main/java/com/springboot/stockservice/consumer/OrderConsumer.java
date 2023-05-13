package com.springboot.stockservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springboot.stockservice.dto.OrderEvent;

@Service
public class OrderConsumer {
	private Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void consume(OrderEvent event) {
		LOGGER.info(
				String.format("Order event recieved => %s", event.toString()));
	}
}
