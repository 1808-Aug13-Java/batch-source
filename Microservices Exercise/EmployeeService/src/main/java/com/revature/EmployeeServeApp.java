package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient // Not necessary, as this is implicit, but better be explicit
@EnableFeignClients
@EnableCircuitBreaker // enables the Hystrix Circuit breaker. 
public class EmployeeServeApp {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServeApp.class, args);
	} // end of main

} // end of class EmployeeServeApp
