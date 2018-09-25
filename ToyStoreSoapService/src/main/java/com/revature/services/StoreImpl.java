package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jws.WebService;

import com.revature.exceptions.StoreFullException;
import com.revature.models.Toy;


@WebService
public class StoreImpl implements Store {
	
	/** A list of toys that this store has. */
	private List<Toy> toys = new ArrayList<>();
	
	/** A random number generator for selecting random toys. */
	private Random rand = new Random();
	
	
	public StoreImpl() {
		toys.add(new Toy("Amazing Ball", 40.00, "Ball"));
		toys.add(new Toy("Basket Ball", 39.95, "Ball"));
		toys.add(new Toy("Base Ball", 2.95, "Ball"));
		toys.add(new Toy("Rubber Ball", 19.97, "Ball"));
		
		toys.add(new Toy("Risk", 39.99, "Board Game"));
		toys.add(new Toy("Monopoly", 1_000_000.00, "Board Game"));
		toys.add(new Toy("Life", 14.97, "Board Game"));
		
		toys.add(new Toy("Marbles (50)", 30D, "Misc"));
		toys.add(new Toy("Jacks", 19.91, "Misc"));
		toys.add(new Toy("Linkin' Logs", 199.50, "Misc"));
	}
	
	
	
	@Override
	public List<Toy> getAllToys() {
		return toys;
	}

	@Override
	public Toy addToy(Toy toy) throws StoreFullException {
		if (this.toys.size() > 9) {
			throw new StoreFullException();
		} else {
		toys.add(toy);
		System.out.println(toy.getName() + " was added");
		return toy;
		}
	}

	@Override
	public Toy getRandomToy() {
		return toys.get(rand.nextInt(toys.size()));
	}
	
	
	
}
