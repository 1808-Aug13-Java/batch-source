package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.models.Toy;

@WebService
public interface Store {
	public List<Toy> getAllToys();
	public Toy addToy(Toy toy);
	public Toy getRandomToy();
}
