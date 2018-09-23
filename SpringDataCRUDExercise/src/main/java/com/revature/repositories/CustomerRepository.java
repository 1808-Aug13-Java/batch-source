package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
