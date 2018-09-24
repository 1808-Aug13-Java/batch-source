package com.revature.repositories;

import org.springframework.stereotype.Repository;

import com.revature.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
