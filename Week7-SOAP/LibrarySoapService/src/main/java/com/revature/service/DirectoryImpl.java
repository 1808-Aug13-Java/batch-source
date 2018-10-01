package com.revature.service;
import java.util.List;
import java.util.ArrayList;
import com.revature.model.Address;
import javax.jws.WebService;
@WebService(endpointInterface="com.revature.service.Directory")
public class DirectoryImpl implements Directory {
  private List<Address> addressList = new ArrayList<Address>();
  public List<Address> getAddresses() {
    return addressList;
  }
  public Address getAddress(long ownerId) {
    for(Address a : addressList) {
      if(a.getOwnerId() == ownerId) {
        return a;
      }
    } 
    return null;
  }
  //public int addAddress(Address a) {

  //}
}
