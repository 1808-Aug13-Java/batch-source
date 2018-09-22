package com.revature.springdata.models; 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.*;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column
  private String phone;
  @Column
  private String firstname;
  @Column
  private String lastname;


  public Customer() {};
  public Customer(String firstname, String lastname, String phone) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;

  }
  @Override
  public String toString() {
    return String.format("ID: %d, firstname: %s, lastname: %s, phone: %s", id, firstname, lastname, phone);
  }  
  /**
   * Get id.
   *
   * @return id as Long.
   */
  public Long getId()
  {
      return id;
  }
  
  /**
   * Set id.
   *
   * @param id the value to set.
   */
  public void setId(Long id)
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
   * Get firstname.
   *
   * @return name as String.
   */
  public String getFirstname()
  {
      return firstname;
  }
  
  /**
   * Set firstname.
   *
   * @param name the value to set.
   */
  public void setFirstname(String firstname)
  {
      this.firstname = firstname;
  }
  
  /**
   * Get lastname.
   *
   * @return lastname as String.
   */
  public String getLastname()
  {
      return lastname;
  }
  
  /**
   * Set lastname.
   *
   * @param lastname the value to set.
   */
  public void setLastname(String lastname)
  {
      this.lastname = lastname;
  }
}
