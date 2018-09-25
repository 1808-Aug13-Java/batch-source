package com.revature.service;

import javax.jws.WebService;

@WebService(endpointInterface="com.revature.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHi(String text) {
		System.out.println(text);
		return text;
	}

}
