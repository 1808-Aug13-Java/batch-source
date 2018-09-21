package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Beverage;
import com.revature.services.BeverageService;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

	@Autowired
	BeverageService bs;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Beverage> findAllBeverages() {
		return bs.findAllBeverages();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Beverage findBeverageById(@PathVariable("id") Integer id) {
		return bs.findBeverageById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createBeverage(@RequestBody Beverage b) {
		System.out.println("Create Bevereage: " + b);
		bs.createBeverage(b);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateBeverage(@RequestBody Beverage b) {
		bs.updateBeverage(b);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void deleteBeverage(@RequestBody Beverage b) {
		bs.deleteBeverage(b);
	}	
	
	@DeleteMapping(value="/{id}")
	public void deleteBeverageById(@PathVariable("id") Integer id) {
		bs.deleteBeverageById(id);
	}
}
