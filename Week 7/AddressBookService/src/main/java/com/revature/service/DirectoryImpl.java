package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import com.revature.exception.AddressAlreadyExistsException;
import com.revature.exception.AddressNotPresentException;
import com.revature.models.Address;

@WebService(endpointInterface="com.revature.service.Directory")
public class DirectoryImpl implements Directory{

	private List<Address> addressList = new ArrayList<>();
	
	public DirectoryImpl() {
	
	addressList.add(new Address(1L,"1234 Seasame Street","Capital City","CA",20170));
	}
	
	
	
	@Override
	public List<Address> getAllAddresses() {
		return this.addressList;
	}


	@Override
	public Address getAddressById(long id) throws AddressNotPresentException {
		Iterator addressItr= addressList.iterator();
		boolean flag=false;
		Address current= new Address();
		while(addressItr.hasNext()) {
			current = (Address) addressItr.next();
			if (current.getOwnerId()==id) {
				flag=true;
				break;
			}
		}
		if (!flag) {
			throw new AddressNotPresentException("No address with that identifier");
		}
		
		
		return current;
	}



	@Override
	public int addAddress(Address a) throws AddressAlreadyExistsException {
		int result =0;
		if(this.addressList.contains(a)) {
			throw new AddressAlreadyExistsException();
		}else {
			this.addressList.add(a);
			result=1;
			
		}
		
		return result;
	}

}
