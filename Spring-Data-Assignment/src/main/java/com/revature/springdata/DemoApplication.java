package com.revature.springdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import com.revature.springdata.repository.CustomerRepository;
import com.revature.springdata.models.Customer;
@SpringBootApplication
public class DemoApplication {
  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      repository.save(new Customer("jack", "bauer", "55"));
      log.info("Customers found with find all");
      for(Customer c : repository.findAll()) {
        log.info(c.toString());
      }
    };
  }
}
