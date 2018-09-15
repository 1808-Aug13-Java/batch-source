package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public Invoice getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Invoice invoice = (Invoice) s.get(Invoice.class, id);
		s.close();
		return invoice;
	}

	@Override
	public List<Invoice> getInvoice() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Invoice");
		List<Invoice> invoices = q.list();
		s.close();
		return invoices;
	}

	@Override
	public int createInvoice(Invoice invoice) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoicePk = (int) s.save(invoice);
		tx.commit();
		s.close();
		
		return invoicePk;
	}

	@Override
	public void updateInvoice(Invoice invoice) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(invoice);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Invoice invoice = (Invoice) s.get(Invoice.class, id);
		if (invoice != null) {
			s.delete(invoice);
		}
		
		tx.commit();
		s.close();
	}

}
