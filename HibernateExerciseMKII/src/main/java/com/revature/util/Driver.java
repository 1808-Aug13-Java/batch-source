package com.revature.util;

import org.hibernate.Session;

import com.revature.daos.CustomerDao;
import com.revature.daos.CustomerDaoImpl;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
				s.close();
				
		CustomerDao cd= new CustomerDaoImpl();
//		Customer c= new Customer("Ronald McDonald",  "757-893-3098");
//		cd.createCustomer(c);
//		cd.updateCustomerById(1);
//		cd.deleteCustomerById(1);
//			
	}
}
 