package com.revature.springdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import com.revature.springdata.repositories.CustomerRepository;
import com.revature.springdata.controllers.CustomerController;
import com.revature.springdata.models.Customer;
@SpringBootApplication
public class DemoApplication {
  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Bean
  public CommandLineRunner loadCustomers(CustomerRepository repository, CustomerController customerController) {
    return (args) -> {
      repository.save(new Customer("jack", "bauer", "55"));
      repository.save(new Customer("kate", "winslet", "432257"));
      repository.save(new Customer("regis", "philbin", "54394394394395"));
      repository.save(new Customer("hilary", "clinton", "5985"));
      repository.save(new Customer("edwin", "newman", "5125"));
    };
  }
  @Bean
  public CommandLineRunner findCustomers(CustomerRepository repository, CustomerController customerController) {
    return (args) -> {
      log.info("Customers found with find all");
      for(Customer c : customerController.findCustomers()) {
        log.info(c.toString());
      }
    };
  }
}
