package com.revature.util;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.daos.CustomersDao;
import com.revature.daos.CustomersDaoImpl;
import com.revature.daos.InvoicesDao;
import com.revature.daos.InvoicesDaoImpl;
import com.revature.models.Customers;
import com.revature.models.Invoices;

public class Driver {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
		Customers a = new Customers(1,"Jorge",1432512315);
		Customers b = new Customers(2,"George",1322512315);
		Customers cc = new Customers(3,"Maria",1132512315);
		Customers d = new Customers(4,"Manuel",1112512315);
		
//		s.save(a);
		//Invoices ab = new Invoices(1, Date.valueOf("1984-01-23") , 6.53);
		
	//		s.save(ab);
//		tx.commit();
		CustomersDao c = new CustomersDaoImpl();
		c.createCustomers(a);
		c.createCustomers(b);
		c.createCustomers(cc);
		c.createCustomers(d);
		Invoices ab = new Invoices(1, Date.valueOf("1984-01-23") , 6.53);
		Invoices abc = new Invoices(2, Date.valueOf("1994-01-23") , 7.53);
		Invoices abcd = new Invoices(3, Date.valueOf("2000-01-23") , 10.53);
		Invoices ab1 = new Invoices(4, Date.valueOf("2012-01-23") , 13.53);
		
		InvoicesDao i = new InvoicesDaoImpl();
		i.createInvoices(ab);
		i.createInvoices(abc);
		i.createInvoices(abcd);
		i.createInvoices(ab1);
		
		List<Customers> cust = c.getCustomersByName("George");
		System.out.println(cust);
		List<Invoices> invoice = i.getInvoicesByDate(Date.valueOf("1994-01-23"));
		System.out.println(invoice);
		abcd.setAmount(1000);
		i.updateInvoices(abcd);
		cc.setName("Marona");
		c.updateCustomers(cc);
		c.deleteCustomersById(1);
		i.deleteInvoicesById(1);

		s.close();
		
	}

}
