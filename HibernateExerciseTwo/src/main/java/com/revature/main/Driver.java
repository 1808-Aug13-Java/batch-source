package com.revature.main;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		HibernateUtil.getSession().close();
		
		CustomerDao customer = new CustomerDaoImpl();
		customer.createCustomer(new Customer(1, "Alex", 5056666));

	}

}
