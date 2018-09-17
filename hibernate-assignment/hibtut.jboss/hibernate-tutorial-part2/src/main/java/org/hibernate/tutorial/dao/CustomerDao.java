package org.hibernate.tutorial.dao;
import java.util.*;
import org.hibernate.tutorial.domain.Customer;
import org.hibernate.*;
public interface CustomerDao {
  public List<Customer> getCustomers(Session session);
  
}
