package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.*;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		String hql = "from Customer";
		Query q = s.createQuery(hql);
		List<Customer> customers = q.list();
		s.close();
		
		return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("getCustomerById");
		q.setInteger("idVar", id);
		Customer customer = (Customer) q.list().get(0);
		s.close();
		return customer;
	}

	@Override
	public int createCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int customerPK = (int) s.save(c);
		tx.commit();
		s.close();
		return customerPK;
	}

	@Override
	public List<Customer> getCustomersByName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME = ?").addEntity(Customer.class);
		q.setString(0, name);
		List<Customer> customers = q.list();
		s.close();
		
		return customers;
	}

	@Override
	public long getCustomerCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Customer.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return (Long) rows.get(0);
	}

}
