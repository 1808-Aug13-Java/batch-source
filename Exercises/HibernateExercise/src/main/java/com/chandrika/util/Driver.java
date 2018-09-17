package com.chandrika.util;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.chandrika.daos.*;
import com.chandrika.models.*;

public class Driver {

	public static void main(String[] args) {
		// Session s = HibernateUtil.getSession();
		// s.close();
		
		// Create tables and populate them
		/*
		CustomerDao cd = new CustomerDaoImpl();
		cd.createCustomer(new Customer("Dell", "(800) 624-9897"));
		cd.createCustomer(new Customer("Lenovo", "(800) 624-9897"));
		cd.createCustomer(new Customer("Toshiba", "(800) 624-9897"));
		
		List<Customer> Customers = cd.getCustomers();
		Invoice i1 = new Invoice(Date.valueOf("2005-04-21"), 2500000.00, Customers.get(0));
		Invoice i2 = new Invoice(Date.valueOf("2007-03-19"), 8450000.00, Customers.get(1));
		Invoice i3 = new Invoice(Date.valueOf("2005-11-01"), 1200000.00, Customers.get(2));

		InvoiceDao id = new InvoiceDaoImpl();
		id.createInvoice(i1);
		id.createInvoice(i2);
		id.createInvoice(i3);
		*/
		
		// Test dao implementations
		InvoiceDao id = new InvoiceDaoImpl();
		List<Invoice> invoices = id.getInvoicesByCustomer(2);
		for(Invoice i : invoices) {
			System.out.println(i);
		}
	}
}
