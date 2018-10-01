package com.chandrika.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandrika.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
