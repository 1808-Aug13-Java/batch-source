package org.hibernate.tutorial.dao;

import org.hibernate.tutorial.util.HibernateUtil;
import org.hibernate.tutorial.domain.Invoice;
import org.hibernate.Session;
import java.util.*;

public interface InvoiceDao {
  public List<Invoice> getInvoices(Session session);

  public long createInvoice(Session session, Invoice c);
  public void deleteInvoiceById(Session session, long id);

  public Invoice getInvoiceById(Session session, long id);
}
