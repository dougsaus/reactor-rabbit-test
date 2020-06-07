package com.ds3161.reactor.rabbit.test.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RabbitConfig {

	@Bean()
	Mono<Connection> connectionMono(RabbitProperties rabbitProperties) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(rabbitProperties.getHost());
		connectionFactory.setPort(rabbitProperties.getPort());
		connectionFactory.setUsername(rabbitProperties.getUsername());
		connectionFactory.setPassword(rabbitProperties.getPassword());
		return Mono.fromCallable(() -> connectionFactory.newConnection("reactor-rabbit")).cache();
	}

}