package com.revature.daos;

import java.util.List;

import org.hibernate.*;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	public InvoiceDaoImpl() {
		super();
	}

	@Override
	public int createInvoice(Invoice invoice) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int createdInvoicePk = (int) s.save(invoice);
		tx.commit();
		s.close();
		return createdInvoicePk;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Invoice invoice = (Invoice) s.get(Invoice.class, id);
		s.close();
		return invoice;
	}

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		String hql = "from Invoice";
		Query q = s.createQuery(hql);
		List<Invoice> invoices = q.list();
		
		return invoices;
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
	public void deleteInvoice(Invoice invoice) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(invoice);
		tx.commit();
		s.close();
	}

	
}
