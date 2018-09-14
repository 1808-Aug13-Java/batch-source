package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Invoice;
import com.revature.util.InvoiceUtil;

public class InvoiceDaoImp implements InvoiceDao {

	@Override
	public List<Invoice> getInvoice() 
	{
		Session s = InvoiceUtil.getSession();
		List<Invoice> ret = s.createQuery("from Invoice").list();
		s.close();
		return ret;
	}

	@Override
	public Invoice getInvoiceById(int id)
	{
		Session s = InvoiceUtil.getSession();
		Criteria c = s.createCriteria(Invoice.class);
		c.add(Restrictions.eq("id", id));
		List<Invoice> retList = c.list();
		
		
		
		Invoice ret = null;
		if(retList.size() > 0)
			ret = retList.get(0);
		s.close();
		
		return ret;
	}

	@Override
	public int createInvoice(Invoice b) 
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		
		int ret = (Integer)s.save(b);
		t.commit();
		s.close();
		
		return ret;
		
	}

	@Override
	public void updateInvoice(Invoice c)
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		
		s.update(c);
		t.commit();
		s.close();
	}

	@Override
	public void deleteInvoice(int id) 
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		Invoice c = (Invoice)s.get(Invoice.class, id);
		if(c != null)
			s.delete(c);
		t.commit();
		s.close();
		
	}

}
