package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Application {

	public static void main(String[] args) {
		// this is packaged as a jar
		
		//run application in main method
		SpringApplication.run(Application.class, args);
		
		//run this as a Spring Boot Application
		

	}

}
