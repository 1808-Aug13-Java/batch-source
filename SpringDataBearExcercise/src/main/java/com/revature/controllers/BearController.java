package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Bear;
import com.revature.services.BearService;

@RestController
@RequestMapping("/bears")
public class BearController {
	
	@Autowired
	BearService bearService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<Bear> findAllBears(){
		return bearService.findAllBears();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Bear findBearById(@PathVariable("id") Long id) {
		return bearService.findBearById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Bear addBear(@RequestBody Bear u) {
		return bearService.addBear(u);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Bear updateBear(@Valid @RequestBody Bear u) {
		return bearService.updateBear(u);
	}
	
	@DeleteMapping(value="/{id}")
	//@ResponseBody
	public long deleteBear(@PathVariable Long id) {
		return bearService.deleteBear(id);
	}
	
}