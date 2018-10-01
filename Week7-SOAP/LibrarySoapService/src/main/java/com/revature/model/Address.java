package com.revature.model;

public class Address {
  private long ownerId;
  private String streetAddress;
  private String city;
  private String state;
  private int zipcode;

  public Address() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Address(long ownerId, String streetAddress, String city, String state, int zipcode) {
    super();
    this.ownerId = ownerId;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
  }
  public long getOwnerId() {
    return ownerId;
  }
  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }
  public String getStreetAddress() {
    return streetAddress;
  }
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public int getZipcode() {
    return zipcode;
  }
  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
    result = prime * result + zipcode;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Address other = (Address) obj;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (ownerId != other.ownerId)
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    if (streetAddress == null) {
      if (other.streetAddress != null)
        return false;
    } else if (!streetAddress.equals(other.streetAddress))
      return false;
    if (zipcode != other.zipcode)
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "Address [ownerId=" + ownerId + ", streetAddress=" + streetAddress + ", city=" + city + ", state="
      + state + ", zipcode=" + zipcode + "]";
  }
}
