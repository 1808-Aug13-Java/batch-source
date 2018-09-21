package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.User;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		String getRequestUrl = "http://192.168.51.190:8084/users";
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			User user = restTemplate.getForObject(getRequestUrl, User.class);
			log.info("resource consumption successful");
			log.info(user.toString());
		} catch (Exception e) {
			log.error("resource consumption uncessful");
		}
		
		String postRequestUrl = "http:/localhost:8084/users";
//		User newUser = new User(97L, "Lola", "Martin", "lolamart@gmail.com", "lmartin", "pass123");
		User newUser = null;
		
		try {
			User addedUser = restTemplate.postForObject(postRequestUrl, newUser, User.class);
			log.info("resource exchange successful");
			log.info("'Posted' : " + newUser.toString());
		} catch (Exception e) {
			log.error("resourch exchange unsuccessful");
		}
	}

}
