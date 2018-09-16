package com.revature.util;

import java.util.List;

import org.hibernate.Session;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		s.close();
//		Customer c = new Customer("John", "5555555555");
		
		CustomerDao cd = new CustomerDaoImpl();
//		
//		System.out.println(cd.createCustomer(c));
		
		List<Customer> customers = cd.getCustomers();
		System.out.println(customers);
		
		System.out.println(cd.getCustomerById(1));
		
		
	}

}
