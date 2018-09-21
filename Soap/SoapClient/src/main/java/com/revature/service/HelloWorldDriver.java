package com.revature.service;

public class HelloWorldDriver {

	public static void main(String[] args) {
		
		HelloWorldImplService helloService = new HelloWorldImplService();
		HelloWorld hello = helloService.getHelloWorldImplPort();
		
		hello.sayHi("");
	}

}
