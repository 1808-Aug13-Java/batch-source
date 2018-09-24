package com.revature.springdata.models; 
import java.util.*;
import javax.persistence.*;


@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
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
  public Invoice(Date created, int amount) {
    this.created = created;
    this.amount = amount;
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
