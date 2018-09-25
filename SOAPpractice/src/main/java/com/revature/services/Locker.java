package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.exepctions.ShoeException;
import com.revature.model.Sneaker;

@WebService
public interface Locker {

	public List<Sneaker> getAllShoes();
	public String addShoe(Sneaker sneak) throws ShoeException;
}
