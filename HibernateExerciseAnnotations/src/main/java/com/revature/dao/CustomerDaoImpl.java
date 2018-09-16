package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		Customer c = (Customer) s.get(Customer.class, id);
		s.close();
		return c;
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
	public void updateCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customer c = (Customer) s.get(Customer.class, id);
		if(c != null) {
			s.delete(c);
		}
		tx.commit();
		s.close();
	}

}
