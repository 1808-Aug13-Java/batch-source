package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Beverage;
import com.revature.repositories.BeverageRepository;

@Service
public class BeverageService {

	@Autowired
	BeverageRepository br;
	
	public List<Beverage> findAllBeverages() {
		return br.findAll();
	}
	
	public Beverage findBeverageById(Integer id) {
		return br.getOne(id);
	}
	
	public void createBeverage(Beverage b) {
		br.save(b);
	}
	
	public void updateBeverage(Beverage b) {
		br.save(b);
	}
	
	public void deleteBeverage(Beverage b) {
		br.delete(b);
	}
	
	public void deleteBeverageById(Integer id) {
		br.deleteById(id);
	}
}
