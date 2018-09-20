package com.revature.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping(value="/hello", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String displayMessage() {
		return "Hello from our Spring Boot App";
	}
}
