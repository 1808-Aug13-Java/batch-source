package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Customer;
import com.revature.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao{

	public int createCustomer(Customer c) {
		Session s = HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		int result=(int) s.save(c);
		tx.commit();
		s.close();
		return result;
	}
	public int deleteCustomerById(int customerId) {
		Session s =HibernateUtil.getSession();
		Query q =s.createSQLQuery("DELETE FROM CUSTOMER WHERE CUSTOMER_ID= ? ").addEntity(Customer.class);
		q.setInteger(0, customerId);
		int result =q.executeUpdate();
		return result;
	}
	public int updateCustomerById(int customerId) {
		Session s=HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		Customer c= (Customer) s.load(Customer.class, customerId);//makes a detached object persistent again
		System.out.println(c);
		return 1;
		
	};
	public Customer getCustomerById(int customerId) {
		Session s = HibernateUtil.getSession();
		Customer c=(Customer) s.load(Customer.class, customerId);
		System.out.println(c);
		s.close();
		
		return c;
	};
	public List<Customer> getAllCustomers(){
		Session s=HibernateUtil.getSession();
		String hql="from Customer";
		Query q= s.createQuery(hql);
		List<Customer> customers=q.list();
		s.close();
		return customers;
	}
}
