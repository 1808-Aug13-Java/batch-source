package com.chandrika.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.chandrika.models.Invoice;
import com.chandrika.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	@Override
	public List<Invoice> getInvoices() {
		Session s = HibernateUtil.getSession();
		List<Invoice> Invoices = s.createQuery("from Invoice").list();
		s.close();
		return Invoices;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createInvoice(Invoice b) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int InvoicePK = (int) s.save(b);
		tx.commit();
		s.close();
		return InvoicePK;
	}

	@Override
	public List<Invoice> getInvoicesByCustomer(int CustomerId) {
		Session s = HibernateUtil.getSession();
		String hql = "from Invoice where Customer = :CustomerVar";
		Query q = s.createQuery(hql);
		q.setInteger("CustomerVar", CustomerId);
		List<Invoice> Invoices = q.list();
		return Invoices;
	}

}
