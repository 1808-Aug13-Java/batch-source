package com.revature.util;

import org.hibernate.Session;

import com.revature.daos.CustomerDao;
import com.revature.daos.CustomerDaoImpl;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {

		Session con = HibernateUtil.getSession();
		CustomerDao cdi = new CustomerDaoImpl();
		Customer c = new Customer();
		c.setName("Ed");
		c.setNumber("999-888-7777");
		cdi.createCustomer(c);
		cdi.getCustomers();
		con.close();
	}
}