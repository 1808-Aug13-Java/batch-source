package org.hibernate.tutorial.domain;
import java.util.*;

public class Customer {
  private long id;
  private String phone;
  private String name;

  public Customer() {};
  
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
   * Get phone.
   *
   * @return phone as String.
   */
  public String getPhone()
  {
      return phone;
  }
  
  /**
   * Set phone.
   *
   * @param phone the value to set.
   */
  public void setPhone(String phone)
  {
      this.phone = phone;
  }
  
  /**
   * Get name.
   *
   * @return name as String.
   */
  public String getName()
  {
      return name;
  }
  
  /**
   * Set name.
   *
   * @param name the value to set.
   */
  public void setName(String name)
  {
      this.name = name;
  }
}
