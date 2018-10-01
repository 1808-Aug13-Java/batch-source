package com.revature.service;
import java.util.List;
import com.revature.model.Address;
import javax.jws.WebService;

@WebService
public interface Directory {
  public List<Address> getAddresses();
  public Address getAddress(long ownerId);
}
