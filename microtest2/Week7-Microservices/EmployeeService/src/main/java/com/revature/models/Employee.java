package com.revature.models;
public class Employee {
  private String firstname;
  private String lastname;
  private String username;
  private Reimbursement reimbursement;

  public Employee() {}
  public Employee(String firstname, String lastname, String username, Reimbursement reimbursement) {
    this.firstname=firstname;
    this.lastname=lastname;
    this.username=username;
    this.reimbursement=reimbursement;
  }
  
  /**
   * Get firstname.
   *
   * @return firstname as String.
   */
  public String getFirstname()
  {
      return firstname;
  }
  
  /**
   * Set firstname.
   *
   * @param firstname the value to set.
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
  
  /**
   * Get username.
   *
   * @return username as String.
   */
  public String getUsername()
  {
      return username;
  }
  
  /**
   * Set username.
   *
   * @param username the value to set.
   */
  public void setUsername(String username)
  {
      this.username = username;
  }
  
  
  /**
   * Get reimbursement.
   *
   * @return reimbursement as Reimbursement.
   */
  public Reimbursement getReimbursement()
  {
      return reimbursement;
  }
  
  /**
   * Set reimbursement.
   *
   * @param reimbursement the value to set.
   */
  public void setReimbursement(Reimbursement reimbursement)
  {
      this.reimbursement = reimbursement;
  }
}
