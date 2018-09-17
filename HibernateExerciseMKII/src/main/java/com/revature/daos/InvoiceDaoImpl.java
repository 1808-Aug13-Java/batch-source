package com.revature.daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao{

	@Override
	public int createInvoice(Invoice i) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		int result=(int) s.save(i);
		tx.commit();
		s.close();
		return result;
	}

	@Override
	public int deleteInvoice(int invoiceId) {
		Session s =HibernateUtil.getSession();
		Query q =s.createSQLQuery("DELETE FROM INVOICE WHERE INVOICE_ID= ? ").addEntity(Invoice.class);
		q.setInteger(0, invoiceId);
		int result =q.executeUpdate();
		return result;
	}

	@Override
	public int updateInvoice(int id, Date date, double amount) {
		Session s=HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		Invoice c= (Invoice) s.load(Invoice.class, id);//makes a detached object persistent again
		c.setAmount(amount);
		c.setDate(date);
		int result = (int) s.save(c);
		tx.commit();
		s.close();
		return result;
	}

	@Override
	public Invoice getInvoiceById(int invoiceId) {
		Session s = HibernateUtil.getSession();
		Invoice c=(Invoice) s.load(Invoice.class, invoiceId);
		System.out.println(c);
		s.close();
		return c;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		Session s=HibernateUtil.getSession();
		String hql="from Invoice";
		Query q= s.createQuery(hql);
		List<Invoice> invoices=q.list();
		s.close();
		return invoices;
	}

}
