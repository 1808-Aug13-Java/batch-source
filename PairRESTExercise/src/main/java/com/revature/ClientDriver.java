package com.revature;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.revature.models.Beverage;

public class ClientDriver {

	private static final String URI_BASE = "http://192.168.61.185:8084/beverages";
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Beverage b = new Beverage(1,"Coke", 22.0f, true, "ZER0");
			restTemplate.postForObject(URI_BASE, b, Beverage.class);
			
			b = restTemplate.getForObject(URI_BASE + "/1", Beverage.class);
			System.out.println(b);
			
			Beverage addB = new Beverage(2,"Pepsi", 44.0f, false, "Classic");
			restTemplate.postForObject(URI_BASE, addB, Beverage.class);
			
			b = restTemplate.getForObject(URI_BASE + "/2", Beverage.class);
			System.out.println(b);
			
			
			addB.setFlavor("Code Red");
			restTemplate.put(URI_BASE, addB);
			
			b = restTemplate.getForObject(URI_BASE + "/2", Beverage.class);
			System.out.println(b);
			
			Beverage destroyB = new Beverage(1, "Doesn't matter", 0.00f, true, "Whatever");
			restTemplate.delete(URI_BASE + "/1");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
