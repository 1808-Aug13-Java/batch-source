package com.revature.driver;

import com.revature.util.HibernateUtil;

public class Driver {
	
	public static void main(String[] args) {
		// Initialize Hibernate, by getting a session. 
		HibernateUtil.getSession().close();
		
		
	}
	
}
