package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.CustomerHibernate;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerHibernate, Long>{
	public CustomerHibernate findCustomerByName(String name);   //the query is "findCustomerByName"
}
