package com.revature.daos;

import java.util.List;

import org.hibernate.*;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	public CustomerDaoImpl() {
		// TODO Auto-generated constructor stub
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
	public Customer getCustomerById(int id) {
		Session s = HibernateUtil.getSession();
		Customer customer = (Customer) s.get(Customer.class, id);
		s.close();
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		Session s = HibernateUtil.getSession();
		String hql = "from Customer";
		Query q = s.createQuery(hql);
		List<Customer> customers = q.list();
		
		return customers;
		
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
	public void deleteCustomer(Customer customer) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(customer);
		tx.commit();
		s.close();
	}

}
