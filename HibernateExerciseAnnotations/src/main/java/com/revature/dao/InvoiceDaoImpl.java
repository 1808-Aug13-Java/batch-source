package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		String hql = "from Invoice";
		Query q = s.createQuery(hql);
		List<Invoice> invoices = q.list();
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
	public void deleteInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Invoice i = (Invoice) s.get(Invoice.class, id);
		if(i != null) {
			s.delete(i);
		}
		tx.commit();
		s.close();
	}
	
	@Override
	public long getInvoiceCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Invoice.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return (Long) rows.get(0);
	}

}
