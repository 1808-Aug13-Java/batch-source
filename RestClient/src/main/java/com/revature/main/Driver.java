package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Intern;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
	
		String getRequestUrl = "http://192.168.60.170:8084/intern/1";
		
		//RestTemplate is an object provided by Spring Web which allows us to map resources and send Http Requests
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Intern intern = restTemplate.getForObject(getRequestUrl, Intern.class);
			log.info("resource consumption successful");
			log.info(intern.toString());
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		String postRequestUrl = "http://192.168.60.170:8084/intern";
		Intern newIntern = new Intern(97L,"Maureen","Johnsan","maureenj@nvcc.edu", "jmaureen","ughwahtever", 100L);
		Intern newIntern1 = new Intern(98L,"Chad","Bowinger","chad@nvcc.edu", "chadRules","huntokar", 1L);
		Intern newIntern2 = new Intern(99L,"Dana","Cardinal","themayor@nv.gov", "realDana","jasika", 95L);
		
		try {
			Intern addedIntern = restTemplate.postForObject(postRequestUrl, newIntern, Intern.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+addedIntern.toString());
			Intern addedIntern1= restTemplate.postForObject(postRequestUrl, newIntern1, Intern.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+addedIntern1.toString());
			Intern addedIntern2 = restTemplate.postForObject(postRequestUrl, newIntern2, Intern.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+addedIntern2.toString());
		} catch (Exception e) {
			log.error("Resource exchange unsuccessful");
		}
		

	}

}
