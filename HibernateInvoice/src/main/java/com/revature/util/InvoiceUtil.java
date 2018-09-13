package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class InvoiceUtil 
{
	private static SessionFactory fact;
	private static SessionFactory getSessionFactory()
	{
		if(fact == null)
		{
			Configuration con = new Configuration().configure("hibernate.cnf.xml");
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
			fact = con.buildSessionFactory(sr);
		}
		return fact;
	}

	public static Session getSession()
	{
		return getSessionFactory().openSession();
	}
}
