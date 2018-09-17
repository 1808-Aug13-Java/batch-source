package org.hibernate.tutorial.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration =  new Configuration().configure("./hibernate.cfg.xml");
      try {
        return configuration.buildSessionFactory();
      }
      catch(HibernateException e) {
        System.out.println("INNER EXCEPTION");
        e.printStackTrace();
        throw new HibernateException("throw inner hibernate exception");
      }
    }
    catch (HibernateException e) {
      System.out.println("HEYO");
      e.printStackTrace();
      throw new HibernateException(e);
    }
    catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("HEYOInitial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}
