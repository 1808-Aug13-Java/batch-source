package org.hibernate.tutorial.domain;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
  @SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="INVOICE_PK")
  private long id;
  @Column
  private Date created;
  @Column
  private int amount;

  public Invoice() {}
  
  /**
   * Get id.
   *
   * @return id as long.
   */
  public long getId()
  {
      return id;
  }
  
  /**
   * Set id.
   *
   * @param id the value to set.
   */
  public void setId(long id)
  {
      this.id = id;
  }
  
  /**
   * Get created.
   *
   * @return created as Date.
   */
  public Date getCreated()
  {
      return created;
  }
  
  /**
   * Set created.
   *
   * @param created the value to set.
   */
  public void setCreated(Date created)
  {
      this.created = created;
  }
  
  /**
   * Get amount.
   *
   * @return amount as int.
   */
  public int getAmount()
  {
      return amount;
  }
  
  /**
   * Set amount.
   *
   * @param amount the value to set.
   */
  public void setAmount(int amount)
  {
      this.amount = amount;
  }
}
