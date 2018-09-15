package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer getCustomerById(int custId) {
		Session session = HibernateUtil.getSession();
		Customer cust = (Customer) session.get(Customer.class, custId);
		session.close();
		return cust;
	}

	@Override
	public List<Customer> getCustomers() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		session.close();
		return customers;
	}

	@Override
	public int createCustomer(Customer cust) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int custPK = (int) session.save(cust);
		tx.commit();
		session.close();
		return custPK;
	}

	@Override
	public void updateCustomer(Customer cust) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(cust);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteCustomerById(int custId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Customer cust = (Customer) session.get(Customer.class, custId);
		
		// If the object exists, delete it. 
		if (cust != null) {
			session.delete(cust);
		}
		
		tx.commit();
		session.close();
	}

}
