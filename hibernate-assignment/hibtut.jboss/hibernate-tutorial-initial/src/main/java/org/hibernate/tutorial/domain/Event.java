package org.hibernate.tutorial.domain;

import java.util.Date;

public class Event {
  private long id;
  private String title;
  private Date date; 

  public Event() {} 

  public long getId() { return id; }
  public String getTitle() { return title; }
  public Date getDate() { return date; } 

  public void setId(long id) {
    this.id = id;
  }
  public void setTitle(String title) {
    this.title=title;
  }
  public void setDate(Date date) {
    this.date=date;
  }
}

