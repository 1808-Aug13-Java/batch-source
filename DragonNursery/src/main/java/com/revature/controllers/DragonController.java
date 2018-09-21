package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Dragon;
import com.revature.services.DragonService;

@RestController
@RequestMapping("/dragonNursery")
public class DragonController {
	
	@Autowired
	DragonService dragonService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody // removed for RESTful demo
	public List<Dragon> findAllDragons(){
		return dragonService.getDragons();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Dragon findDragonById(@PathVariable("id") Long id) {
		return dragonService.getDragonById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Dragon addDragon(@Valid @RequestBody Dragon u) {
		return dragonService.createDragon(u);
	}
	
	@PutMapping(value = "/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Dragon updateDragon(@RequestBody Dragon u, @PathVariable("id") Long id) {
		u.setId(id); 
		return dragonService.updateDragon(u);
	}
	
	@DeleteMapping(value = "/{id}")
	//@ResponseBody
	public Dragon deleteDragon(@PathVariable("id") Long id) {
		return dragonService.deleteDragon(dragonService.getDragonById(id));
	}
	
	@PatchMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Dragon patchDragon(@RequestBody Dragon u, @PathVariable("id") Long id) {
		u.setId(id);
		return  dragonService.updateDragon(u);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	public Dragon metadataCar() {
		return null;
	}
	
}
