package org.hibernate.tutorial.domain;
import java.util.*;

public class Invoice {
  private long id;
  private Date created;
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
