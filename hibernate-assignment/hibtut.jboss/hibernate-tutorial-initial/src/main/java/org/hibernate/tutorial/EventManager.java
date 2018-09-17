package org.hibernate.tutorial;

import org.hibernate.tutorial.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import java.util.*;
import org.hibernate.tutorial.dao.*;
import org.hibernate.tutorial.domain.*;

public class EventManager {
  public static void main(String ... args) {
    EventManager mgr = new EventManager();
    SessionFactory sf = HibernateUtil.getSessionFactory(); 

    CustomerDaoImpl cdao = new CustomerDaoImpl();
    Session s = sf.openSession();
    long id = cdao.createCustomer(s, new Customer());
    s.close();

    s = sf.openSession();
    List<Customer> custs = cdao.getCustomers(s);
    for(Customer c : custs) {
      System.out.println(c);
    }
    s.close();
    HibernateUtil.getSessionFactory().close();
  }
}
