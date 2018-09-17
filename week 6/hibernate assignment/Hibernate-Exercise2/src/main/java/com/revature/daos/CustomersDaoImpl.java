package com.revature.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.models.Customers;
import com.revature.util.HibernateUtil;

public class CustomersDaoImpl implements CustomersDao{

	@Override
	public List<Customers> getCustomers(){
		Session s = HibernateUtil.getSession();
		String hql = "from Customers";
		Query q = s.createQuery(hql);
		List<Customers> c = q.list();
		s.close();
		return c;
		
	}
	@Override
	public Customers getCustomersById(int id) {
		Session s = HibernateUtil.getSession();
		Customers c = (Customers) s.get(Customers.class, id);
		s.close();
		return c;
	}
	@Override
	public int createCustomers(Customers c) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int custPK = (int) s.save(c);
		tx.commit();
		s.close();
		return custPK;
		
	}
	@Override
	public List<Customers> getCustomersByName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM CUSTOMERS WHERE CUST_NAME = ?").addEntity(Customers.class);
		q.setString(0, name);
		List<Customers> c = q.list();
		s.close();
		return c;
	}
	@Override
	public List<Customers> getCustomersByPhone(long phone) {
		Session s = HibernateUtil.getSession();
		Query q = s.createSQLQuery("SELECT * FROM CUSTOMERS WHERE CUST_PHONE = ?").addEntity(Customers.class);
		q.setLong(0, phone);
		List<Customers> c = q.list();
		s.close();
		return c;
	}
	@Override
	public long getCustomersCount() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Customers.class);
		c.setProjection(Projections.rowCount());
		List<Long> rows = c.list();
		s.close();
		return (Long) rows.get(0);
	}
	@Override
	public void deleteCustomersById(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Customers c = (Customers) s.get(Customers.class, id);
		if(c != null) {
		s.delete(c);
		}
		tx.commit();
		s.close();
	}
	@Override
	public void updateCustomers(Customers c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(c);
		tx.commit();
		s.close();
		
		
	}
}
