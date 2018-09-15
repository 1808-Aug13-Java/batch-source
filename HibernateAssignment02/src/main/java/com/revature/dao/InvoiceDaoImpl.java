package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public Invoice getInvoiceById(int invId) {
		// Get a hibernate session from the session factory
		Session session = HibernateUtil.getSession();
		
		// Get the invoice from the session. Use get to fully initialize. 
		Invoice inv = (Invoice) session.get(Invoice.class, invId);
		
		// Close the session and return the invoice. 
		session.close();
		return inv;
	}

	@Override
	public List<Invoice> getInvoices() {
		// Get a hibernate session from the session factory
		Session session = HibernateUtil.getSession();
		// Create a query to load all of invoices
		Query query = session.createQuery("from Invoice");
		List<Invoice> invoices = query.list();
		
		// Close the session and return the invoices
		session.close();
		return invoices;
	}

	@Override
	public int createInvoice(Invoice inv) {
		// Get a session from the factory, and begin a transaction.
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();
		
		// Save the object, getting the primary key from it. 
		int invPK = (int) session.save(inv);
		
		// Commit the transaction, and return the PK.
		trans.commit();
		session.close();
		return invPK;
	}

	@Override
	public void updateInvoice(Invoice inv) {
		// Get a session from the factory, and begin a transaction.
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();
		
		// Update the invoice. 
		session.update(inv);
		
		// Commit the transaction. 
		trans.commit();
		session.close();
	}

	@Override
	public void deleteInvoiceById(int invId) {
		// Get a session from the factory, and begin a transaction.
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();
		
		// Attempt to get the invoice. 
		Invoice inv = (Invoice) session.get(Invoice.class, invId);
		
		// If there is an invoice by the provided id, delete it. 
		if (inv != null) {
			session.delete(inv);
		}
		
		// Commit the transaction. 
		trans.commit();
		session.close();
	}

}
