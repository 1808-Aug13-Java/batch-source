package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO{

	public CustomerDAOImpl() {
		super();
	}

	public List<Customer> getCusomers() {
		Session s = HibernateUtil.getSession();
		String hql = "from Customer";
		Query q = s.createQuery(hql);
		List<Customer> customers = q.list();
		s.close();
		return customers;
	}

	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer c = (Customer) s.get(Customer.class, id);
		s.close();
		return c;
	}

	public int createCustomer(Customer cust) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int custId = (Integer) s.save(cust);
		tx.commit();
		s.close();
		return custId;
	}

	@Override
	public int deleteCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return 0;
	}

}
