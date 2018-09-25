package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Group;
import com.revature.services.GroupService;

@CrossOrigin
@RestController
public class GroupController {
	
	@Autowired
	GroupService groupService;

	@GetMapping("/api/groups")
	public List<Group> listAllGroups() {
		return groupService.listAllGroups();
	}
	
	@GetMapping("/api/groups/{name}")
	public Group findGroupByName(@PathVariable("name") String name) {
		return groupService.findGroupByName(name);
	}
	
	@PostMapping("/api/groups/{name}")
	public Group createGroup(@RequestBody Group group) {
		return groupService.createGroup(group);
	}
	
// TODO rest of the RESTful routes :P
	
}
