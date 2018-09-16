package com.revature.util;

import org.hibernate.Session;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
		
//		CustomerHibernate c1 = new CustomerHibernate("cindy peng", "clpeng@gmail.com", "5107797669");
//		CustomerHibernate c2 = new CustomerHibernate("pindy ceng", "plceng@gmail.com", "5109779667");
//		CustomerHibernate c3 = new CustomerHibernate("mindy ceng", "mceng@gmail.com", "5109504632");
		
//		CustomerHibernateDao cd = new CustomerHibernateDaoImpl();
//		System.out.println(cd.getCustomers());					//test list customers
		
//		System.out.println(cd.getCustomerById(1));
		
//		System.out.println(cd.createCustomer(c1));		//need to do these every time i think because we have
//		System.out.println(cd.createCustomer(c2));    //"create" instead of validate. it's recreating our tables
//		System.out.println(cd.createCustomer(c3));
//		
//		CustomerHibernate cUpdate = cd.getCustomerById(2);
//		cUpdate.setEmail("hellopanda@fkmail.com");
//		cd.updateCustomer(cUpdate);
//		
//		cd.deleteCustomerById(2);
		
		
		
//		InvoiceHibernate i1 = new InvoiceHibernate((float)234.23, 1, Date.valueOf("2017-03-06"));
//		InvoiceHibernate i2 = new InvoiceHibernate((float)500.23, 2, Date.valueOf("2017-08-06"));
//		InvoiceHibernate i3 = new InvoiceHibernate((float)19762.23, 3, Date.valueOf("2017-12-06"));
//		
//		InvoiceHibernateDao invDao = new InvoiceHibernateDaoImpl();
//		
//		invDao.createInvoice(i1);
//		invDao.createInvoice(i2);
//		invDao.createInvoice(i3);
//		invDao.createInvoice(i3);
//		
//		System.out.println(invDao.getInvoices());
		
//		InvoiceHibernate iUpdate = invDao.getInvoiceById(1);
//		iUpdate.setAmount((float) 45271.48);
//		invDao.updateInvoice(iUpdate);
//		
//		invDao.deleteInvoiceById(4);
		
	}
}
