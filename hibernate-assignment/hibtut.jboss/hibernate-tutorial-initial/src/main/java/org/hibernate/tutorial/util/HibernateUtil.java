package org.hibernate.tutorial.util;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.service.*;

public class HibernateUtil {
  private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    return new Configuration().configure().buildSessionFactory();
  }
  public static SessionFactory getSessionFactory() {
    return sessionFactory; 
  }
}
