package com.revature.util;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.Invoice;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
	}


}
