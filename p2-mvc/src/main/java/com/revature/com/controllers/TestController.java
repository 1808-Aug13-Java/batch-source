package com.revature.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	@GetMapping(value="/test")
	@ResponseBody
	public String testMethod() {
		return "test";
	}
}
