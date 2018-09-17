package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	public List<Invoice> getInvoices() {
		 Session s = HibernateUtil.getSession();
		 String hql = "from Invoice";
		 Query q = s.createQuery(hql);
		 List<Invoice> invoices  = q.list();
		 s.close();
		return invoices;
	}

	public Invoice getInvoiceById(int id) {
		Session s = HibernateUtil.getSession();
		Invoice i = (Invoice) s.get(Invoice.class, id);
		s.close();
		return i;
	}

	public int createInvoice(Invoice inv) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int invoicePk = (Integer)  s.save(inv);
		tx.commit();
		s.close();
		return invoicePk;
	}

	public int updateInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(id);
		tx.commit();
		s.close();
		return 1;
	}

	public int deleteInvoice(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(id);
		tx.commit();
		s.close();
		return 1;
	}

}
