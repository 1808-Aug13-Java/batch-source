package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.InvoiceAnnot;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public List<InvoiceAnnot> getInvoices() {
		Session s = HibernateUtil.getSession();
		List<InvoiceAnnot> invoices = s.createQuery("from InvoiceAnnot").list();
		s.close();
		return invoices;
	}

	@Override
	public List<InvoiceAnnot> getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		String hql = "from InvoiceAnnot where id = :idVar";
		Query q = s.createQuery(hql);
		q.setInteger("idVar", id);
		List<InvoiceAnnot> invoice = q.list();
		s.close();
		return invoice;
	}

	@Override
	public int createInvoice(InvoiceAnnot i) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoicePK = (int) s.save(i);
		tx.commit();
		s.close();
		return invoicePK;
	}

	@Override
	public void deleteInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		InvoiceAnnot i = (InvoiceAnnot) s.get(InvoiceAnnot.class, id);
		if (i != null) {
		    s.delete(i);
		}
		tx.commit();
		s.close();
	}

	@Override
	public int updateInvoice(InvoiceAnnot i) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoicePK = (int) s.save(i);
		tx.commit();
		s.close();
		return invoicePK;
	}

}
