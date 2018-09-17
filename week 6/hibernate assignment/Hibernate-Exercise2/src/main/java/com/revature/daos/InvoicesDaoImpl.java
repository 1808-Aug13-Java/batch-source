package com.revature.daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.models.Invoices;
import com.revature.util.HibernateUtil;

public class InvoicesDaoImpl implements InvoicesDao{

	@Override
	public List<Invoices> getInvoices(){
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Invoices");
		List<Invoices> i = q.list();
		s.close();
		return i;
		
	}
	
	@Override
	public Invoices getInvoicesById(int id) {
		Session s = HibernateUtil.getSession();
		Invoices i = (Invoices) s.get(Invoices.class, id);
		s.close();
		return i;
		
	}
	
	@Override
	public int createInvoices(Invoices c) {
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invPK = (int) s.save(c);
		tx.commit();
		s.close();
		return invPK;
	}
	
	@Override
	public List<Invoices> getInvoicesByDate(Date dates){
	
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM INVOICES WHERE DATES = ?").addEntity(Invoices.class);
		q.setDate(0, dates);
		List<Invoices> i = q.list();
		s.close();
		return i;
	}
	@Override
	public long getInvoicesCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Invoices.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return rows.get(0);
	}
	@Override
	public void deleteInvoicesById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Invoices i = (Invoices) s.get(Invoices.class, id);
		if(i != null) {
			s.delete(i);

		}
		tx.commit();
		s.close();
		
	}
	@Override
	public void updateInvoices(Invoices c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}
	
}
