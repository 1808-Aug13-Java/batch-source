package com.revature.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.CustomerAnnot;
import com.revature.util.HibernateUtil;

public class CustomerAnnotDaoImpl implements CustomerAnnotDao {

	@Override
	public List<CustomerAnnot> getCustomer() {
		Session s = HibernateUtil.getSession();
		List<CustomerAnnot> customer = s.createQuery("from CustomerAnnot").list();
		s.close();
		return customer;
	}

	@Override
	public List<CustomerAnnot> getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		String hql = "from CustomerAnnot where id = :idVar";
		Query q = s.createQuery(hql);
		q.setInteger("idVar", id);
		List<CustomerAnnot> customers = q.list();
		s.close();

		return customers;
	}

	@Override
	public int createCustomer(CustomerAnnot c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int customerPK = (int) s.save(c);
		tx.commit();
		s.close();
		return customerPK;
	}


	@Override
	public void deleteCustomer(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		CustomerAnnot c = (CustomerAnnot) s.get(CustomerAnnot.class, id);
		if (c != null) {
		    s.delete(c);
		}
		tx.commit();
		s.close();
	}

	@Override
	public void updateCustomer(CustomerAnnot c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

}
