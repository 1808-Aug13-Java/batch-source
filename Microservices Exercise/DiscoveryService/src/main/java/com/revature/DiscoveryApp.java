package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Remember, this is implicit, but better be explicit anyway. 
public class DiscoveryApp {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApp.class, args);
	} // end of main

} // end of class DiscoveryApp
