package com.revature.utils;

import org.hibernate.Session;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
	}
}
