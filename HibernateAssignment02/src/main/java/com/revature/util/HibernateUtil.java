package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	// Prevents Instantiation
	private HibernateUtil() {}
	
	
	private static SessionFactory sessionFactory;
	
	
	private static SessionFactory getSessionFactory(String filename) {
		// If the session factory insn't initilized
		if (HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure(filename);
			ServiceRegistry sr = 
					new StandardServiceRegistryBuilder()
										.applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
		}
		
		return HibernateUtil.sessionFactory;
	} // end of getSessionFactory
	
	
	
	public static Session getSession() {
		return getSessionFactory("hibernate.cfg.xml").openSession();
	}
	
	
} // end of class HibernateUtil









