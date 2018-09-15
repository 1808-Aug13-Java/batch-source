package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer customer = (Customer) s.get(Customer.class, id);
		s.close();
		
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		Query query = s.createQuery("from Customer");
		List<Customer> customers = query.list();
		s.close();
		return customers;
	}

	@Override
	public int createCustomer(Customer customer) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int customerPk = (int) s.save(customer);
		tx.commit();
		s.close();
		return customerPk;
	}

	@Override
	public void updateCustomer(Customer customer) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(customer);
		tx.commit();
		s.close();

	}

	@Override
	public void deleteCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customer customer = (Customer) s.get(Customer.class, id);
		
		if (customer != null) {
			s.delete(customer);
		}
		
		tx.commit();
		s.close();

	}

}
