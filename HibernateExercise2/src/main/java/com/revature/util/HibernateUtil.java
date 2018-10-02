package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	public HibernateUtil() {
		super();
	}
	
	private static SessionFactory getSessionFactory(String filename) {
		if (HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure(filename);
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
		}
		return HibernateUtil.sessionFactory;
	}
	
	public static Session getSession() {
		return HibernateUtil.getSessionFactory("hibernate.cfg.xml").openSession();
	}
}