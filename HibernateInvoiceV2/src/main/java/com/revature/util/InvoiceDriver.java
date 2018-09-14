package com.revature.util;

import java.sql.Date;

import org.hibernate.Session;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImp;
import com.revature.dao.InvoiceDao;
import com.revature.dao.InvoiceDaoImp;
import com.revature.models.Customer;
import com.revature.models.Invoice;

public class InvoiceDriver {

	public static void main(String[] args) {
		Session darthSessions = InvoiceUtil.getSession();
		darthSessions.close();
		CustomerDao cd = new CustomerDaoImp();
		
		Customer jlj = new Customer("John Jacko", 6719032);
		Customer mtc = new Customer("Miranda T. C.", 5555555);
		
		cd.createCustomer(jlj);
		cd.createCustomer(mtc);
		
		InvoiceDao id = new InvoiceDaoImp();
		
		id.createInvoice(new Invoice(Date.valueOf("2018-05-05"), 234.43f, mtc));
		id.createInvoice(new Invoice(Date.valueOf("2018-05-12"), 45.43f, jlj));
		
		//

	}

}
