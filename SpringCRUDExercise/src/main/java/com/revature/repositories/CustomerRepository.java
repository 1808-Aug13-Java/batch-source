package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>{

}
