package com.revature.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Bear;
import com.revature.exceptions.BearNotFoundException;
import com.revature.services.BearService;

@Controller
@CrossOrigin
public class BearController {
	
	@Autowired
	BearService bearService;
	
	//@RequestMapping(value="/bears", method=RequestMethod.GET)
	@GetMapping("/bears")
	@ResponseBody
	public List<Bear> getBears(){
		return bearService.getBears();
	}
	
	@GetMapping("/bears/{id}")
	@ResponseBody
	public Bear getBearByPathId(@PathVariable("id") int id) {
		Bear b = bearService.getBearById(id);
		if(b == null) {
			throw new BearNotFoundException();
		}
		return b;
	}
	
	@RequestMapping(value="bears/search", method=RequestMethod.GET)
	public String getSearchPage() {
		return "SearchBear";
	}
	
//	@RequestMapping(value="bears/search", method=RequestMethod.POST)
//	public String getBear(@RequestParam("id") int bearId) {
//		return "redirect:/bears/"+bearId;
//	}
	
	@RequestMapping(value="bears/search", method=RequestMethod.POST)
	public String getBear(HttpServletRequest req) {
		String bearId = req.getParameter("id");
		return "redirect:/bears/"+bearId;
	}
	
	@RequestMapping(value="/bears/create", method=RequestMethod.GET)
	public String getCreateBear() {
		return "CreateBear";
	}
	
	@RequestMapping(value="bears/create", method=RequestMethod.POST)
	public String addBear(@RequestParam("name") String name, @RequestParam("birthday") Date birthday) {
		bearService.addBear(new Bear(name, birthday));
		return "redirect:/bears";
	}

}
