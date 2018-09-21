package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.models.Dragon;

public class Driver {
	//get all, get one, create, update, delete
	
	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		//new dragon
		Dragon newDragon = new Dragon(2L, "Charles", "fire", "wyvern", 200);
		
		Dragon newDragon1 = new Dragon(6L, "Bo", "earth", "Yellow Reaper", 400);
		
		String getRequestUrl = "http://192.168.60.135:8090/dragonNursery";
		
		try {
		Dragon d = restTemplate.getForObject(getRequestUrl, Dragon.class);
		log.info("resource consumption successful (get ALL)");
		log.info(d.toString());
		} catch (Exception e){
			log.error(e.getMessage());
		}
		
		try {
		Dragon d = restTemplate.getForObject(getRequestUrl + "/2", Dragon.class);
		log.info("resource consumption successful (get one)");
		log.info(d.toString());
		} catch (Exception e){
			log.error(e.getMessage());
		}
		
		try {
		Dragon d2 = restTemplate.postForObject(getRequestUrl, newDragon, Dragon.class);
		log.info("dragon created successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		try {
		Dragon d3 = restTemplate.postForObject(getRequestUrl, newDragon1, Dragon.class);
		log.info("dragon created successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		
		try {
		restTemplate.put(getRequestUrl + "/2", newDragon, Dragon.class);
		log.info("dragon updated successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		try {
			//restTemplate.delete(getRequestUrl + "/6", Dragon.class);
			Long iddd = newDragon1.getId();
			
			restTemplate.delete(getRequestUrl + "/6", newDragon1);
			
			
			log.info("dragon deleted successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

}
