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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Intern;
import com.revature.services.InternService;

@RestController
//@Controller
@RequestMapping("/interns")
public class InternController {
	

	@Autowired
	InternService internService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<Intern> findAllUsers(){
		return internService.findAllInterns();
	}
	
	@GetMapping(value="/{internId}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Intern findUserById(@PathVariable("internId") Long id) {
		return internService.findInternById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Intern addUser(@Valid @RequestBody Intern u) {
		return internService.addIntern(u);
	}
	
	@PutMapping(value="/{internId}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Intern updateUser(@Valid @RequestBody Intern u, @PathVariable("internId") Long id) {
		u.setInternId(id);
		return internService.updateIntern(u);
	}
	
	@RequestMapping(value="/{internId}",method = RequestMethod.DELETE)
	public void deleteUser( @PathVariable("internId") Long id) {
//		u.setInternId(id);
		 internService.deleteIntern(id);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	public Intern metadataCar() {
		return null;
	}


}
