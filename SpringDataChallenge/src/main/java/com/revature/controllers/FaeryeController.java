package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Faerye;
import com.revature.services.FaeryeService;

@RestController
@RequestMapping("/faeryes")
public class FaeryeController {
	
	@Autowired
	FaeryeService faeryeService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<Faerye> findAllUsers(){
		return faeryeService.findAllFaeryes();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Faerye findUserById(@PathVariable("id") Long id) {
		return faeryeService.findFaeryeById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Faerye addUser(@RequestBody Faerye f) {
		return faeryeService.addFaerye(f);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Faerye updateUser(@RequestBody Faerye f) {
		return faeryeService.updateFaerye(f);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Faerye deleteUser(@PathVariable("id") Long id) {
		Faerye fa = faeryeService.findFaeryeById(id);
		return faeryeService.deleteFaerye(fa);
	}
}