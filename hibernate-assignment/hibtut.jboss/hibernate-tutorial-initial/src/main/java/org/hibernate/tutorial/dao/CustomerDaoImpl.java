package org.hibernate.tutorial.dao;

import org.hibernate.tutorial.util.HibernateUtil;
import org.hibernate.tutorial.domain.Customer;
import org.hibernate.Session;
import java.util.*;

public class CustomerDaoImpl implements CustomerDao {
  public CustomerDaoImpl() {
  }
  public List<Customer> getCustomers(Session session) {
    return session.createQuery("from Customer").list();
  }

  public long createCustomer(Session session, Customer c) {
    session.beginTransaction();
    long id = (long) session.save(c);
    session.getTransaction().commit();
    return id;
  }
  public void deleteCustomerById(Session session, long id) {
    session.beginTransaction();
    Customer c = session.get(Customer.class, id);
    if(c != null) {
      session.delete(c);
      session.getTransaction().commit();
    }
  }

  Customer getCustomerById(Session session, long id) {
    session.beginTransaction();
    return session.get(Customer.class, id);
  }
}
