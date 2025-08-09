package com.arsenic.rabbitmq_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RabbitmqEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqEurekaServerApplication.class, args);
	}

}
