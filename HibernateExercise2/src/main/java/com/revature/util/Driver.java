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
		InvoiceDao idi = new InvoiceDaoImpl();
		CustomerDao cdi = new CustomerDaoImpl();
		
		long count = idi.getInvoiceCount();
		
		Customer c1 = new Customer(2, "Smith", 3233341341L);
		Customer c2 = new Customer(3, "Jones", 5131718342L);
		Customer c3 = new Customer(4, "Mike", 8131555349L);
		Customer c4 = new Customer(5, "John", 2331543349L);
		
		Invoice i1 = new Invoice(1,Date.valueOf("2018-08-05"), 19.32, 1);
		Invoice i2 = new Invoice(1,Date.valueOf("2018-08-25"), 30.35, 2);
		Invoice i3 = new Invoice(1,Date.valueOf("2018-03-12"), 17.94, 3);
		Invoice i4 = new Invoice(1,Date.valueOf("2018-05-04"), 19.94, 4);
		Invoice i5 = new Invoice(1,Date.valueOf("2018-02-28"), 18.49, 1);
		
		
		cdi.createCustomer(c1);
		cdi.createCustomer(c2);
		cdi.createCustomer(c3);
		cdi.createCustomer(c4);
		
		idi.createInvoice(i1);
		idi.createInvoice(i2);
		idi.createInvoice(i3);
		idi.createInvoice(i4);
		idi.createInvoice(i5);
		
		
		
		System.out.println(idi.getInvoices());
		
		System.out.println(idi.getInvoiceCount());
		
		System.out.println(cdi.getCustomers());
		
		System.out.println(cdi.getCustomerById(1));
		
		System.out.println(cdi.getCustomersByName("John"));
		
		System.out.println(idi.getInvoiceById(3));
		
		System.out.println(idi.getInvoices());		
		
		System.out.println(idi.getInvoiceCount());
		
		System.out.println(cdi.getCustomerCount());
	}

	
}
