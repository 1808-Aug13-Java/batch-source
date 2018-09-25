package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.exceptions.TooManyBearsException;
import com.revature.models.Bear;

@WebService(endpointInterface = "com.revature.service.BearService")
public class BearServiceImpl implements BearService{

	private List<Bear> bearList = new ArrayList<>();
	
	public BearServiceImpl() {
		bearList.add(new Bear("Bearnie Sanders", "Yellowstone Cave", 1887));
		bearList.add(new Bear("Yogi", "Yellowstone Cave", 1960));
		bearList.add(new Bear("Ice Bear", "North Pole", 2008));
		bearList.add(new Bear("Boo Boo", "McDonald's", 2018));
	}
	
	
	@Override
	public List<Bear> getAllBears() {
	return this.bearList;
	}

	@Override
	public String addBear(Bear bear) throws TooManyBearsException {
		if(this.bearList.size()>3) {
			throw new TooManyBearsException("Library full. Cannot add "+bear.getName());
		}else {
			this.bearList.add(bear);
			return bear.getName()+" added to the library";
		}
		
	}


	@Override
	public String deleteBear(String name) {
		for(int i = 0; i<bearList.size(); i++) {
			if(bearList.get(i).getName().equals(name)) {
				bearList.remove(i);
				break;
			}
			else {
			
			}
		}
		return name;
	}

	
	
}
