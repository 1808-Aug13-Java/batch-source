package com.revature.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Customers;

public class Driver {

	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		String getRequestUrl = "http://192.168.60.87:8084/customers/1";
		String requestUrl = "http://192.168.60.87:8084/customers";
		Customers newCustomer = new Customers((long) 2, "Josh", 1234567);
		
		RestTemplate restTemplate = new RestTemplate();
		
		// Get all customers
		try {
			Customers customer = restTemplate.getForObject(getRequestUrl, Customers.class);
			log.info("resource consumption successful");
			log.info(customer.toString());
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		// Insert Customer
		try {
			Customers postCustomer = restTemplate.postForObject(requestUrl, newCustomer, Customers.class);
			log.info("resource consumption successful");
			log.info(postCustomer.toString());
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		// Insert & update Customer
		newCustomer = new Customers((long) 3, "Dan", 1234567);
		try {
			Customers postCustomer = restTemplate.postForObject(requestUrl, newCustomer, Customers.class);
			log.info("resource consumption successful");
			log.info(postCustomer.toString());
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		newCustomer.setName("Maoijdoaiwd");
		try {
			restTemplate.put(requestUrl + "/" + newCustomer.getId(), newCustomer);
			log.info("resource consumption successful");
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
		// Delete customer by ID
		try {
			restTemplate.delete(requestUrl + "/" + newCustomer.getId());
			log.info("resource consumption successful");
		} catch (Exception e) {
			log.error("resource consumption unsuccessful");
		}
		
	}

}