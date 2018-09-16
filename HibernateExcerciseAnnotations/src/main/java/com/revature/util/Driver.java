package com.revature.util;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.InvoiceDao;
import com.revature.dao.InvoiceDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Invoice;

public class Driver {
	public static void main(String[] args){
		Session s = HibernateUtil.getSession();
		s.close();
		
		CustomerDao cd = new CustomerDaoImpl();
		Customer myCustomer = new Customer();
		cd.createCustomer(myCustomer);
		cd.createCustomer(new Customer());
		cd.createCustomer(new Customer());
		
		List<Customer> customers = cd.getCustomers();
		Invoice b1 = new Invoice();
		Invoice b2 = new Invoice();
		Invoice b3 = new Invoice();
		
		
		InvoiceDao id = new InvoiceDaoImpl();
		
		id.createInvoice(b1);
		id.createInvoice(b2);
		id.createInvoice(b3);
		
		InvoiceDao id1 = new InvoiceDaoImpl();
		List<Invoice> invoices = id.getInvoiceByName("Ice Invoice");
		System.out.println(invoices);
		System.out.println(id.getInvoiceCount());
		
		List<Invoice> invoices2 = id.getInvoicesByCustomer(7);
		for(Invoice i: invoices) {
			System.out.println(i);
	}
}}
