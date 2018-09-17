package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.models.Invoice;
import com.revature.util.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO{

	public CustomerDAOImpl() {
		super();
	}
	@Override
	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		List<Customer> customers = s.createQuery("from Customer").list();
		s.close();
		return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer c = (Customer) s.get(Customer.class, id);
		s.close();
		return c;
	}

	@Override
	public int createCustomer(Customer cust) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int custId = (Integer) s.save(cust);
		tx.commit();
		s.close();
		return custId;
	}

	@Override
	public void deleteCustomer(Customer cust) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customer c = (Customer) s.get(Customer.class, cust.getId());
		if(c != null) {
			s.delete(c);
		}
		tx.commit();
		s.close();
	}

}
