package com.revature.main;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Invoice;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
String getRequestUrl = "http://localhost:8084/invoice";
		
		//RestTemplate is an object provided by Spring Web which allows us to map resources and send Http Requests
		RestTemplate restTemplate = new RestTemplate();
		
//		try {
//			User user = restTemplate.getForObject(getRequestUrl, User.class);
//			log.info("resource consumption successful");
//			log.info(user.toString());
//		} catch (Exception e) {
//			log.error("resource consumption unsuccessful");
//		}
		
		String postRequestUrl = "http://localhost:8084/invoice";
		Invoice newInvoice =new Invoice();
		newInvoice.setAmount(700);
		newInvoice.setDate(Date.valueOf("2018-1-2"));
		try {
			Invoice addedInvoice = restTemplate.postForObject(postRequestUrl, newInvoice, Invoice.class);
			log.info("Resource exchange successful");
			log.info("'Posted' : "+newInvoice.toString());
		} catch (Exception e) {
			log.error("Resource exchange unsuccessful");
		}
	}

}
