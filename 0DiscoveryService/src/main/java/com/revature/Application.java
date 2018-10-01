package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //:O put Server on the main class, then Client on the other services...
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
