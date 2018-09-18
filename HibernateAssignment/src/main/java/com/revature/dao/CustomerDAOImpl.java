package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO{

	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		List<Customer> customers = s.createQuery("from Customer").list();
		s.close();
		return customers;
	}

	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer c = (Customer) s.get(Customer.class, id);
		s.close();
		return c;
	}

	public int createCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int custPK = (int) s.save(c);
		tx.commit();
		s.close();
		return custPK;
	}

	public List<Customer> getCustomerByName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM CUSTOMER WHERE CUST_NAME = ?").addEntity(Customer.class);
		q.setString(0, name);
		List<Customer> customers = q.list();
		return customers;
	}

	public long getCustomerCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Customer.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		return (Long) rows.get(0);
	}

	
	public void deleteCustomer(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customer c = (Customer) s.get(Customer.class, id);
		if(c != null) {
			s.delete(c);
		}
		tx.commit();
		s.close();
	}
	
	public void updateCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

}
