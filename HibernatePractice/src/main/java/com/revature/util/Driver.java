package com.revature.util;

import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {
		
		CustomerDAO cdao = new CustomerDAOImpl();
		Customer customer = new Customer(1, "JJJohnas", "55555555");
		cdao.createCustomer(customer);
		
		Customer c = cdao.getCustomerById(1);
		
		System.out.println(c.toString());
		
	}

}
