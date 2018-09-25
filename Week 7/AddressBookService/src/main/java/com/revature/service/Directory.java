package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.exception.AddressAlreadyExistsException;
import com.revature.exception.AddressNotPresentException;
import com.revature.models.Address;

@WebService
public interface Directory {

	public List<Address> getAllAddresses();
	public Address getAddressById(long id) throws AddressNotPresentException;
	public int addAddress(Address a) throws AddressAlreadyExistsException;
}
