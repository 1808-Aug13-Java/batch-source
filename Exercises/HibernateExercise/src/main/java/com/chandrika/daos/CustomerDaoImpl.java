package com.chandrika.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.chandrika.models.Customer;
import com.chandrika.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		String hql = "from Customer";
		Query q = s.createQuery(hql);
		q.list();
		List<Customer> Customers = q.list();
		s.close();
		return Customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer c = (Customer) s.get(Customer.class, id);
		s.close();
		return c;
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
		if (c!=null) {
			s.delete(c);
		}
		tx.commit();
		s.close();
	}

	@Override
	public int createCustomer(Customer customer) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int CustomerPK = (int) s.save(customer);
		tx.commit();
		s.close();
		return CustomerPK;
	}
	
}
