package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//@Import(AccountsWebApplication.class)
@SpringBootApplication
@EnableEurekaClient
public class Application {
	public static void main(String[] args) {
    //System.setProperty("spring.config.name", "accounts-server");
		SpringApplication.run(Application.class, args);

	}

}
