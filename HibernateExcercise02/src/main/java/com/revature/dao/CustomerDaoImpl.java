package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	public List<Customer> getCustomers() {
		 Session s = HibernateUtil.getSession();
		 String hql = "from Customer";
		 Query q = s.createQuery(hql);
		 List<Customer> customers  = q.list();
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
		int customerPk = (Integer)  s.save(c);
		tx.commit();
		s.close();
		return customerPk;
	}

	public void updateCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
	}

	public int deleteCustomer(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(id);
		tx.commit();
		s.close();
		return 1;
	}

}
