package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Faerye;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		String getRequestUrl = "http://localhost:8084/faeryes/1";
		
		//RestTemplate is an object provided by Spring Web which allows us to map resources and send Http Requests
		RestTemplate restTemplate = new RestTemplate();
		try {
		Faerye faerye = restTemplate.getForObject(getRequestUrl, Faerye.class);
		log.info("resource consumption successful");
		log.info(faerye.toString());
		}catch (Exception e) {
			log.error("Resource consumption unsuccessful");
		}
		
		Faerye newFaerye = new Faerye(6L,"Lola","Bumblepond","Falling",false);
		String postRequestUrl = "http://localhost:8084/faeryes/";
		try {
			Faerye addedFaerye = restTemplate.postForObject(postRequestUrl,  newFaerye,  Faerye.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+addedFaerye.toString());
		}catch(Exception e) {
			log.error("unsuccesssful");
		}
		
		Faerye upFaerye = new Faerye(6L,"Lola","Bumblepond","Flying",false);
		String putRequestUrl = "http://localhost:8084/faeryes/";
		try {
			restTemplate.put(putRequestUrl, upFaerye, Faerye.class);
			String upStr = upFaerye.toString();
			log.info("updated with faerye: "+ upStr);
		}catch(Exception e) {
			log.error("bad");
		}
		String delRequestUrl = "http://localhost:8084/faeryes/";
		try {
			restTemplate.delete(delRequestUrl+"/"+upFaerye.getFaeryeId());
			log.info("thingdel'd");
		}catch(Exception e) {
			log.error("thing not del'd");
		}
	}
}