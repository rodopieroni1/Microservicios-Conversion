package com.in28minutes.microservicios.nammingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NammingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NammingServerApplication.class, args);
	}

}
