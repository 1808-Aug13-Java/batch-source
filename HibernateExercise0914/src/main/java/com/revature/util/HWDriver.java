package com.revature.util;

import java.util.List;

import com.revature.dao.CustomerAnnotDao;
import com.revature.dao.CustomerAnnotDaoImpl;
import com.revature.dao.InvoiceDao;
import com.revature.dao.InvoiceDaoImpl;
import com.revature.models.CustomerAnnot;
import com.revature.models.InvoiceAnnot;

public class HWDriver {
	
	
	public static void main(String[] args) {
		
		/*
		Session s = HibernateUtil.getSession();
		s.close();
		*/
		
		CustomerAnnotDao cda = new CustomerAnnotDaoImpl();
	
		CustomerAnnot c1 = new CustomerAnnot("Rick Perry", "256-555-9933");
		CustomerAnnot c2 = new CustomerAnnot("Marjorie Smithers", "918-555-9944");
		CustomerAnnot c3 = new CustomerAnnot("Annabeth Gish", "504-555-9955");
		CustomerAnnot c4 = new CustomerAnnot("Quentin Gagliano", "314-555-9966");
		
		InvoiceDao id = new InvoiceDaoImpl();
		System.out.println(id.createInvoice(new InvoiceAnnot("2018-08-12", 24.95, c1)));
		id.createInvoice(new InvoiceAnnot("2018-09012", 15.78, c2));
		/*
		cda.createCustomer(c1);
		cda.createCustomer(c2);
		cda.createCustomer(c3);
		cda.createCustomer(c4);
		
		
		List<CustomerAnnot> customers = cda.getCustomer();
		for(CustomerAnnot c : customers) {
			System.out.println(c);
		}
		*/
		//cda.deleteCustomer(1);
		
		
		//cda.updateCustomer(new CustomerAnnot("Marjorie A. Smithers", "918-555-9812"));
		
		
	}

}
