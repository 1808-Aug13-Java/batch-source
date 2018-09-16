package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.InvoiceHibernate;
import com.revature.util.HibernateUtil;

public class InvoiceHibernateDaoImpl implements InvoiceHibernateDao {

	@Override
	public List<InvoiceHibernate> getInvoices() {
		Session s = HibernateUtil.getSession();
		String hql = "from InvoiceHibernate";
		Query q = s.createQuery(hql);
		List<InvoiceHibernate> list = q.list();
		s.close();
		return list;
	}

	@Override
	public InvoiceHibernate getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		InvoiceHibernate invoice = (InvoiceHibernate) s.get(InvoiceHibernate.class, id);
		s.close();
		return invoice;
	}

	@Override
	public int createInvoice(InvoiceHibernate c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int numRowsCreated = (int) s.save(c);
		tx.commit();
		s.close();
		return numRowsCreated;
	}

	@Override
	public void updateInvoice(InvoiceHibernate c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteInvoiceById( int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		InvoiceHibernate invoice = (InvoiceHibernate) s.get(InvoiceHibernate.class, id);
		if( invoice != null )
			s.delete(invoice);
		tx.commit();
		s.close();
	}

		
}
