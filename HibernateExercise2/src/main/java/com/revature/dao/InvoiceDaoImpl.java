package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.*;

import com.revature.util.*;

import com.revature.models.Invoice;

public class InvoiceDaoImpl implements InvoiceDao{

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		String hql = "from Invoice";
		Query q = s.createQuery(hql);
		List<Invoice> invoices = q.list();
		
		return invoices;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("getInvoiceById");
		q.setInteger("idVar", id);
		Invoice invoice = (Invoice) q.list().get(0);
		return invoice;
	}

	@Override
	public int createInvoice(Invoice i) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int InvoicePK = (int) s.save(i);
		tx.commit();
		s.close();
		return InvoicePK;
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
