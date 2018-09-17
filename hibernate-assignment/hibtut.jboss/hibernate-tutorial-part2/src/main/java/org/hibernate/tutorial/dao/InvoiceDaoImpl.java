package org.hibernate.tutorial.dao;

import org.hibernate.tutorial.util.HibernateUtil;
import org.hibernate.tutorial.domain.Invoice;
import org.hibernate.Session;
import java.util.*;

public class InvoiceDaoImpl implements InvoiceDao {
  public InvoiceDaoImpl() {
  }
  public List<Invoice> getInvoices(Session session) {
    return session.createQuery("from Invoice").list();
  }

  public long createInvoice(Session session, Invoice c) {
    session.beginTransaction();
    long id = (long) session.save(c);
    session.getTransaction().commit();
    return id;
  }
  public void deleteInvoiceById(Session session, long id) {
    session.beginTransaction();
    Invoice c = session.get(Invoice.class, id);
    if(c != null) {
      session.delete(c);
      session.getTransaction().commit();
    }
  }

  public Invoice getInvoiceById(Session session, long id) {
    session.beginTransaction();
    return session.get(Invoice.class, id);
  }
}
