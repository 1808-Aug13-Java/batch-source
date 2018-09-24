package com.revature.springdata.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.revature.springdata.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>  {
  public List<Customer> findByLastname(String lastname);
}

