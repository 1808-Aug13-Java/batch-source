package com.revature.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.CustomerExersize;
import com.revature.models.InvoiceExersize;
import com.revature.util.HibernateUtil;

public class InvoiceDao implements InvoiceDaoInterface {

	@Override
	public List<InvoiceExersize> getAllInvoices() {
		Session s = HibernateUtil.getSession();
		String hql = "from InvoiceExersize";
		Query q = s.createQuery(hql);
		List<InvoiceExersize> inv = q.list();
		s.close();
		return inv;
	}

	@Override
	public InvoiceExersize getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		InvoiceExersize inv = (InvoiceExersize) s.get(InvoiceExersize.class, id);
		s.close();
		return inv;
	}

	@Override
	public int newInvoice(InvoiceExersize inv) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoices = (int) s.save(inv);
		tx.commit();
		s.close();
		return invoices;
	}

	@Override
	public void updateInvoice(InvoiceExersize inv) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		s.update(inv);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		InvoiceExersize inv = (InvoiceExersize) s.get(InvoiceExersize.class, id);
		if (inv != null)
			s.delete(inv);
		tx.commit();
		s.close();
	}

}
