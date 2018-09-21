package com.revature.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Customer;

public class Driver {
	
	private static final Logger log = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		
		String getRequestUrl = "http://192.168.60.54:8084/customers/1";

		//RestTemplate is an object provide by Spring Web which
		//allows us to map resources and send Http Requests
		RestTemplate restTemplate = new RestTemplate();
		
		
		try {
			Customer user = restTemplate.getForObject(getRequestUrl, Customer.class);
			log.info("resouce consumption successful");
			log.info(user.toString());
		}catch(Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		String postRequestUrl = "http://192.168.60.54:8084/customers";
		Customer newUser = new Customer(15,"Test", "1234567890");
		
		try {
			Customer addedUser = restTemplate.postForObject(postRequestUrl, newUser, Customer.class);
			log.info("resource exchange successful");
			log.info("Posted: "+newUser.toString());
		}catch(Exception e) {
			log.error("resource exchange unsuccessful");
		}
		
		String updateRequestUrl = "http://192.168.60.54:8084/customers/6";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Customer newUser1 = new Customer(6,"Testing Id #6", "5554443333");
//		Customer newUser1 = new Customer();
//		String json = new ObjectMapper().writeValueAsString(newUser1);
//		newUser1.
		HttpEntity<Customer> newCustomer = new HttpEntity<Customer>(newUser1, headers);
		
		try {
			restTemplate.put(updateRequestUrl, newCustomer);
			log.info("resource exchange successful");
		}catch(Exception e) {
			log.error("resource exchange unsuccessful");
		}
		
//		String deleteRequestUrl = "http://192.168.60.54:8084/customers/{id}";
		String deleteRequestUrl = "http://192.168.60.54:8084/customers/3";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id","3");
//		Customer newUser2 = new Customer(4,"Test", "1234567890");
//		Customer newUser3 = restTemplate.getForObject(getRequestUrl, Customer.class);
		
		try {
			restTemplate.delete(deleteRequestUrl);
//			restTemplate.delete(deleteRequestUrl, params);
//			restTemplate.delete(deleteRequestUrl, Customer.class);
//			restTemplate.delete(deleteRequestUrl, restTemplate.getForObject(getRequestUrl, Customer.class));
//			restTemplate.exchange(deleteRequestUrl, HttpMethod.DELETE, new HttpEntity<Customer>(), String.class)
			log.info("resource exchange successful");
		}catch(Exception e) {
			log.error("resource exchange unsuccessful");
		}
		
		
		
		
		
	}
	
	

}
