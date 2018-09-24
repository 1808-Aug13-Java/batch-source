package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService
{
	@Autowired
	CustomerRepository custRepo;

	@Override
	public List<Customer> findAllCustomers() 
	{
		
		return custRepo.findAll();
	}

	@Override
	public Customer findCustomerById(Integer id)
	{
		
		return custRepo.getOne(id);
	}

	@Override
	public Customer addCustomer(Customer newCustomer) 
	{
		
		return custRepo.save(newCustomer);
	}

	@Override
	public Customer updateCustomer(Customer u) 
	{
//		Customer c = custRepo.getCustomerByname(u.getName());
//		if(c == null)
//			return u;
//		c.setName(u.getName());
//		c.setPhone(u.getPhone());
		return custRepo.save(u);
	}

	@Override
	public Customer deleteCustomer(Customer u) 
	{
		custRepo.delete(u);
		return u;
	}

}
