package com.revature.util;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customers;
import com.revature.models.Invoices;

public class Driver {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customers a = new Customers(1432512315,"Jorge",1);
		s.save(a);
		Invoices ab = new Invoices(1, Date.valueOf("1984-01-23") , 6.53);
		s.save(ab);
		tx.commit();
		s.close();
		
	}

}
