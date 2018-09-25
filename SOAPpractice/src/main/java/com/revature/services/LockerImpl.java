package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.exepctions.ShoeException;
import com.revature.model.Sneaker;


@WebService(endpointInterface="com.revature.services.Locker")
public class LockerImpl implements Locker {

	private List<Sneaker> sneaks = new ArrayList<>();
	
	public LockerImpl() {
		sneaks.add(new Sneaker("New Jords", "Nikes", 79.99f, "ladies"));
		sneaks.add(new Sneaker("Ernest Hemmingways", "adidas", 210f, "male"));
		sneaks.add(new Sneaker("Kirk Picard", "Enterprise", 45f, "unisex"));
		sneaks.add(new Sneaker("Gambinos", "Childish", 100f, "male"));
		sneaks.add(new Sneaker("Oxford", "Johnston and Murphey", 215.89f, "female"));
		
	}
	
	@Override
	public List<Sneaker> getAllShoes() {
		return this.sneaks;
	}

	@Override
	public String addShoe(Sneaker sneak) throws ShoeException {
		if(sneaks.size() > 10) {
			throw new ShoeException("You got too many shoes sir or madam. Cannot add " + sneak.getName());
		}else {
			this.sneaks.add(sneak);
			return sneak.getName() + " added to locker";
		}
		
	}

	

}
