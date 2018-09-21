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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Celebrity;
import com.revature.services.CelebrityService;

@RestController
@RequestMapping("/celebrities")
public class CelebrityController {
	@Autowired
	CelebrityService celebService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Celebrity> findAllCelebrities() {
		return celebService.findAllCelebrities();
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/{id}")
	public Celebrity findCelebrityById(@PathVariable("id") Integer id) {
		return celebService.findCelebrityById(id);
	}

	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Celebrity addCelebrity(@RequestBody Celebrity c) {
		return celebService.addCelebrity(c);
	}

	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE, value="/{id}")
	public Celebrity updateCelebrity(@RequestBody Celebrity c, @PathVariable("id") Integer id) {
		c.setId(id);
		return celebService.updateCelebrity(c);
	}

	/*
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE, value="/{id}")
	public Celebrity deleteCelebrity(@RequestBody Celebrity c, @PathVariable("id") Integer id) {
		c.setId(id);
		System.err.println("DELETE METHOD CALLED: "+c);
		return celebService.deleteCelebrity(c);
	}*/
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public Celebrity deleteCelebrity(@PathVariable("id") Integer id) {
		Celebrity c = celebService.findCelebrityById(id);
		return celebService.deleteCelebrity(c);
	}
}
