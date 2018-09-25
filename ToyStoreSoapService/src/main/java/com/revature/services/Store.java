package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.exceptions.StoreFullException;
import com.revature.models.Toy;

@WebService
public interface Store {
	public List<Toy> getAllToys();
	public Toy addToy(Toy toy) throws StoreFullException;
	public Toy getRandomToy();
}
