package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDAOImpl implements InvoiceDAO{

	public InvoiceDAOImpl() {
		super();
	}

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		List<Invoice> invoices = s.createQuery("from Invoice").list();
		s.close();
		return invoices;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Invoice i = (Invoice) s.get(Invoice.class, id);
		s.close();
		return i;
	}

	@Override
	public int createInvoice(Invoice inv) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invId = (Integer) s.save(inv);
		tx.commit();
		s.close();
		return invId;
	}

	@Override
	public void deleteInvoice(Invoice inv) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Invoice i = (Invoice) s.get(Invoice.class, inv.getId());
		if(i != null) {
			s.delete(i);
		}
		tx.commit();
		s.close();
	}

	@Override
	public List<Invoice> getInvoicesByCustomer(int customerId) {
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("getInvoicesByCustomer");
		q.setInteger("invoiceVar", customerId);
		List<Invoice> invoices = q.list();
		s.close();
		return invoices;
	}

}
