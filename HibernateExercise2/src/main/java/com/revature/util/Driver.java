package com.revature.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.daos.CustomerDao;
import com.revature.daos.InvoiceDao;
import com.revature.models.CustomerExersize;
import com.revature.models.InvoiceExersize;

public class Driver {
	public static void main(String[] args) {
		Session sess = HibernateUtil.getSession();
		CustomerDao cd = new CustomerDao();
		InvoiceDao id = new InvoiceDao();
		
		CustomerExersize c1 = new CustomerExersize("Bucky", 9999999999L);
		CustomerExersize c2 = new CustomerExersize("Ranger", 12345667890L);
		CustomerExersize c3 = new CustomerExersize("Phil", 9876543210L);
		InvoiceExersize i1 = new InvoiceExersize(Date.valueOf("1995-03-03"), 33.33);
		InvoiceExersize i2 = new InvoiceExersize(Date.valueOf("1995-03-04"), 123);
		InvoiceExersize i3 = new InvoiceExersize(Date.valueOf("1995-03-05"), 5.25);
		InvoiceExersize i4 = new InvoiceExersize(Date.valueOf("1995-03-06"), 99999);
		InvoiceExersize i5 = new InvoiceExersize(Date.valueOf("1995-03-07"), 52.6);
		InvoiceExersize i6 = new InvoiceExersize(Date.valueOf("1995-03-08"), 12.97);
		InvoiceExersize i7 = new InvoiceExersize(Date.valueOf("1995-03-09"), 4);
		InvoiceExersize i8 = new InvoiceExersize(Date.valueOf("2000-01-23"), 5);
		InvoiceExersize i9 = new InvoiceExersize(Date.valueOf("2001-04-04"), 129995.95);

		
		c1.setId(cd.newCustomer(c1));
		c2.setId(cd.newCustomer(c2));
		c3.setId(cd.newCustomer(c3));

		i1.setId(id.newInvoice(i1));
		i2.setId(id.newInvoice(i2));
		i3.setId(id.newInvoice(i3));
		i4.setId(id.newInvoice(i4));
		i5.setId(id.newInvoice(i5));
		i6.setId(id.newInvoice(i6));
		i7.setId(id.newInvoice(i7));
		i8.setId(id.newInvoice(i8));
		i9.setId(id.newInvoice(i9));
		
		
		List<CustomerExersize> cust = cd.getAllCustomers();
		List<InvoiceExersize> inv = id.getAllInvoices();
		for (CustomerExersize c: cust)
			System.out.println(c);
		for (InvoiceExersize i: inv)
			System.out.println(i);
		
		c1.addInvoice(i1);
		c1.addInvoice(i2);
		c2.addInvoice(i3);
		c2.addInvoice(i4);
		c2.addInvoice(i5);
		c2.addInvoice(i6);
		c2.addInvoice(i7);
		c3.addInvoice(i8);
		c3.addInvoice(i9);
		
		cd.updateCustomer(c1);
		cd.updateCustomer(c2);
		cd.updateCustomer(c3);
		
		id.updateInvoice(i1);
		id.updateInvoice(i2);
		id.updateInvoice(i3);
		id.updateInvoice(i4);
		id.updateInvoice(i5);
		id.updateInvoice(i6);
		id.updateInvoice(i7);
		id.updateInvoice(i8);
		id.updateInvoice(i9);
		
		cust = cd.getAllCustomers();
		inv = id.getAllInvoices();
		for (CustomerExersize c: cust)
			System.out.println(c);
		for (InvoiceExersize i: inv)
			System.out.println(i);
		
		sess.close();
	}
}