package com.ibbanker.models;

public class User {
  private String username;
  private String password; 
  private String firstname; 
  private String lastname; 
  private float balance; 
  
  public User() {
    super();
  }
  public User(String username, String password, String firstname, String lastname, float balance) {
    this.username=username;
    this.password=password;
    this.firstname=firstname;
    this.lastname=lastname;
    this.balance=balance;    
  }

  public void setUsername(String u) {
    username = u;
  } 
  public void setPassword(String p) {
    password = p;
  } 
  public void setFirstname(String f) {
    firstname = f;
  }
  public void setLastname(String l) {
    lastname = l;
  }
  public void setBalance(float b) {
    balance = b;
  }
  
  public String getUsername() {
    return username;
  }
  public String getPassword() {
    return password;
  }
  public String getFirstname() {
    return firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public float getBalance() {
    return balance;
  }
}
