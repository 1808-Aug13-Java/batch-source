package com.revature.util;

import org.hibernate.Session;

public class HWDriver {
	
	
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
	}

}
