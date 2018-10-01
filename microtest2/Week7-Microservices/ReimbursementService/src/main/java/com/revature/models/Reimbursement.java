package com.revature.models;

import java.math.BigDecimal;

public class Reimbursement {
  private long reimbursementId;
  private BigDecimal amt;
  private String employee;

  public Reimbursement() {}
  public Reimbursement(long reimbursementId, BigDecimal amt) {
    this.reimbursementId = reimbursementId;
    this.amt = amt;
  }
  
  /**
   * Get reimbursementId.
   *
   * @return reimbursementId as long.
   */
  public long getReimbursementId()
  {
      return reimbursementId;
  }
  
  /**
   * Set reimbursementId.
   *
   * @param reimbursementId the value to set.
   */
  public void setReimbursementId(long reimbursementId)
  {
      this.reimbursementId = reimbursementId;
  }
  
  /**
   * Get amt.
   *
   * @return amt as BigDecimal.
   */
  public BigDecimal getAmt()
  {
      return amt;
  }
  
  /**
   * Set amt.
   *
   * @param amt the value to set.
   */
  public void setAmt(BigDecimal amt)
  {
      this.amt = amt;
  }
  
  
  /**
   * Get employee.
   *
   * @return employee as String.
   */
  public String getEmployee()
  {
      return employee;
  }
  
  /**
   * Set employee.
   *
   * @param employee the value to set.
   */
  public void setEmployee(String employee)
  {
      this.employee = employee;
  }
}


