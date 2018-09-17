package com.revature.util;

import java.sql.Date;

import org.hibernate.Session;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.InvoiceDao;
import com.revature.dao.InvoiceDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Invoice;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
		Customer c1 = new Customer("Cirey Francis", "609-501-1422");
		Customer c2 = new Customer("Mindy Cheng", "403-909-7884");
		Customer c3 = new Customer("Maxewellington Moonstar", "960-853-6052");
		Customer c4 = new Customer("Dick Moonstar", "960-854-6052");
		
		CustomerDao cd = new CustomerDaoImpl();
		cd.createCustomer(c1);
		cd.createCustomer(c2);
		cd.createCustomer(c3);
		cd.createCustomer(c4);
		
		c1.setName("Beef Wellington");
		cd.updateCustomer(c1);
		
		Invoice inv1 = new Invoice(c1, Date.valueOf("2012-09-06"), 100.00f);
		Invoice inv2 = new Invoice(c2, Date.valueOf("2015-12-11"), 160.55f);
		Invoice inv3 = new Invoice(c3, Date.valueOf("2014-10-12"), 120.00f);
		
		InvoiceDao id = new InvoiceDaoImpl();
		
		id.createInvoice(inv1);
		id.createInvoice(inv2);
		id.createInvoice(inv3);
	}

}
