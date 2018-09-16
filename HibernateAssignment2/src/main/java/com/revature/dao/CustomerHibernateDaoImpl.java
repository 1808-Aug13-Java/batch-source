package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.CustomerHibernate;
import com.revature.util.HibernateUtil;

public class CustomerHibernateDaoImpl implements CustomerHibernateDao {

	@Override
	public List<CustomerHibernate> getCustomers() {
		//1. make a session
		Session s = HibernateUtil.getSession();
		//2. make hql statment
		String hql = "from CustomerHibernate";
		//3. use s to make the query. Store the query as a interface Query
		Query q = s.createQuery(hql);
		List<CustomerHibernate> l = q.list();
		s.close();
		return l;
	}

	@Override
	public CustomerHibernate getCustomerById(int id) {
		//make a session
		Session s = HibernateUtil.getSession();
		//use Session's get method     (get method param requires class kind, and the id to get
		CustomerHibernate c = (CustomerHibernate) s.get(CustomerHibernate.class, id);
		//dont forget close session
		s.close();
		return c;
	}

	@Override
	public int createCustomer(CustomerHibernate c) {		//FIXME Adds duplicates :O
		Session s = HibernateUtil.getSession();
		//create a transaction
		Transaction tx = s.beginTransaction();
		//within transaction, use Session's save function to do insertion
		int numInserted = (int) s.save(c);
		//COMMIT transaction(not close) and session
		tx.commit();
		s.close();
		return numInserted;
	}

	@Override
	public void updateCustomer(CustomerHibernate c) { 			//FIXME test these, then add for invoice
		//make session
		//make transaction
		//use Session's .update() method
		//commit transaction
		//close session
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteCustomerById(int id) {
		//same except use s.delete() instead of update. Also check if the object exists in db and isnt null 
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		CustomerHibernate customer = (CustomerHibernate) s.get(CustomerHibernate.class, id);
		if( customer != null )
			s.delete(customer);
		tx.commit();
		s.close();
	}

}
