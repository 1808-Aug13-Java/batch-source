package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		List<Invoice> invoices = s.createQuery("from Invoice").list();
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
	public int createInvoice(Invoice i) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoicePK = (int) s.save(i);
		tx.commit();
		s.close();
		return invoicePK;
	}

	@Override
	public void updateInvoice(Invoice i) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(i);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Invoice i = (Invoice) s.get(Invoice.class, id);
		if(i != null) {
			s.delete(i);
		}
		s.close();
	}

	@Override
	public List<Invoice> getInvoicesByCustomerId(int id) {
		Session s = HibernateUtil.getSession();
		String hql = "from Invoice where customer = :custVar";
		Query q = s.createQuery(hql);
		q.setInteger("custVar", id);
		List<Invoice> invoices = q.list();
		s.close();
		return invoices;
	}

}
