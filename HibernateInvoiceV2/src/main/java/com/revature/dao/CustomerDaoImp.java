package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Customer;
import com.revature.util.InvoiceUtil;

public class CustomerDaoImp implements CustomerDao {

	@Override
	public List<Customer> getCustomer()
	{
		Session s = InvoiceUtil.getSession();
		List<Customer> ret = s.createQuery("from Customer").list();
		s.close();
		return ret;
	}

	@Override
	public Customer getCustomerById(int id) 
	{
		Session s = InvoiceUtil.getSession();
		Criteria c = s.createCriteria(Customer.class);
		c.add(Restrictions.eq("id", id));
		List<Customer> retList = c.list();
		
		
		
		Customer ret = null;
		if(retList.size() > 0)
			ret = retList.get(0);
		s.close();
		
		return ret;
	}

	@Override
	public int createCustomer(Customer b) 
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		
		int ret = (Integer)s.save(b);
		t.commit();
		s.close();
		
		return ret;
	}

	@Override
	public void updateCustomer(Customer c) 
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		
		s.update(c);
		t.commit();
		s.close();
		
	}

	@Override
	public void deleteCustomer(int id) 
	{
		Session s = InvoiceUtil.getSession();
		Transaction t = s.beginTransaction();
		Customer c = (Customer)s.get(Customer.class, id);
		if(c != null)
			s.delete(c);
		t.commit();
		s.close();
	}

}
