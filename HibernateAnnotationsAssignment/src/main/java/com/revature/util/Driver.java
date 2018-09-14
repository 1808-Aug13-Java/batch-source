package com.revature.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.daos.CustomerDao;
import com.revature.daos.CustomerDaoImpl;
import com.revature.daos.InvoiceDao;
import com.revature.daos.InvoiceDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Invoice;

public class Driver {
	
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		
		CustomerDao cdi = new CustomerDaoImpl();
		InvoiceDao idi = new InvoiceDaoImpl();
		Customer c1 = new Customer("Frank", "333-333-3333", null);
		Customer c2 = new Customer("Jenny", "444-444-4444", null);
		Customer c3 = new Customer("Becky", "555-555-5555", null);
		Customer c4 = new Customer("Michael", "666-666-6666", null);
		cdi.createCustomer(c1);
		cdi.createCustomer(c2);
		cdi.createCustomer(c3);
		cdi.createCustomer(c4);
		Invoice i1 = new Invoice(Date.valueOf("2018-01-23"), c1, new BigDecimal(199.99));
		Invoice i2 = new Invoice(Date.valueOf("2015-04-13"), c2, new BigDecimal(109.99));
		Invoice i3 = new Invoice(Date.valueOf("2016-05-29"), c3, new BigDecimal(110.88));
		Invoice i4 = new Invoice(Date.valueOf("2017-12-12"), c4, new BigDecimal(217.43));
		idi.createInvoice(i1);
		idi.createInvoice(i2);
		idi.createInvoice(i3);
		idi.createInvoice(i4);
		List<Customer> customers = cdi.getCustomers();
		for (Customer c: customers) {
			System.out.println(c);
		}
		List<Invoice> invoices = idi.getInvoices();
		for (Invoice i: invoices) {
			System.out.println(i);
		}
		s.close();
	}
}