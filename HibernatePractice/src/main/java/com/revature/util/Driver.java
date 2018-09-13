package com.revature.util;

import org.hibernate.Session;

public class Driver {

	public Driver() {
		super();
	}

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
	}

}
