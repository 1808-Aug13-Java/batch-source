package com.revature.util;

import org.hibernate.Session;

public class Driver {
	
/* 09/13/2018
 *  Create a new project which uses Hibernate 4.3.11, set up a utility class to get sessions to work with
Create a Customer class with an id, a name, and a phone number
Create an Invoice class which includes an invoice id, a date, and amount
Create an hbm file for each class and map them to corresponding entities in the db
Create a new project which maps to the same database using Hibernate Annotations
In the second project create DAO’s implementing CRUD operations for your entities
 */

	public static void main(String[] args) {
		
		Session s = HibernateUtil.getSession();
		s.close();

	}

}
