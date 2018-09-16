package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao{

	@Override
	public List<Invoice> getInvoices() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		List<Invoice> invoice = s.createQuery("from Invoice").list();
		s.close();
		return invoice;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createInvoice(Invoice i) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int customerPK = (int) s.save(i);
		tx.commit();
		s.close();
		return customerPK;
	}

	@Override
	public List<Invoice> getInvoicesByCustomer(int customerId) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("getBearByCave");
		q.setInteger("customerVar", customerId);
		List<Invoice> invoice = q.list();
		s.close();
		return invoice;
	}

	@Override
	public List<Invoice> getInvoiceByName(String name) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM INVOICE WHERE INVOICE_NAME = ?").addEntity(Invoice.class);
		q.setString(0, name);
		List<Invoice> invoice = q.list();
		s.close();
		return invoice;
	}

	@Override
	public long getInvoiceCount() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Invoice.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return (Long) rows.get(0);
	}

}
