package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Intern;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
//		ApplicationContext
	
		String getRequestUrl = "http://192.168.51.190:8084/users/1";
		
		//RestTemplate is an object provided by Spring Web which allows us to map resources and send Http Requests
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Intern intern = restTemplate.getForObject(getRequestUrl, Intern.class);
			log.info("resource consumption successful");
			log.info(intern.toString());
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		String postRequestUrl = "http://localhost:8084/users";
		Intern newIntern = new Intern(97L,"Lola","Martin","lolamart@gmail.com", "lmartin","pass123", 1L);
		
		try {
			Intern addedIntern = restTemplate.postForObject(postRequestUrl, newIntern, Intern.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+newIntern.toString());
		} catch (Exception e) {
			log.error("Resource exchange unsuccessful");
		}
		

	}

}
