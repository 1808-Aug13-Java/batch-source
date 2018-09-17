package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.CustomerExersize;
import com.revature.models.InvoiceExersize;
import com.revature.util.HibernateUtil;

public class CustomerDao implements CustomerDaoInterface {

	@Override
	public List<CustomerExersize> getAllCustomers() {
		Session s = HibernateUtil.getSession();
		String hql = "from CustomerExersize";
		Query q = s.createQuery(hql);
		List<CustomerExersize> cust = q.list();
		s.close();
		return cust;
	}
	

	@Override
	public CustomerExersize getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		CustomerExersize c = (CustomerExersize) s.get(CustomerExersize.class, id);
		s.close();
		return c;
	}

	@Override
	public int newCustomer(CustomerExersize c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int cust = (int) s.save(c);
		tx.commit();
		s.close();
		return cust;
	}

	@Override
	public void updateCustomer(CustomerExersize c) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteCustomer(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		CustomerExersize c = (CustomerExersize) s.get(CustomerExersize.class, id);
		if (c != null)
			s.delete(c);
		tx.commit();
		s.close();
	}

}
