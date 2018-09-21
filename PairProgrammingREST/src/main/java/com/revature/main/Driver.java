package com.revature.main;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Word;

public class Driver {
	private static final Logger log = LoggerFactory.getLogger(Driver.class);
	
	public static void getAll() {
		// GET ALL OPERATION
		String getRequestUrl = "http://192.168.60.95:8084/word";
		
		RestTemplate restTemplate = new RestTemplate();
		
		try { 
			Word word = restTemplate.getForObject(getRequestUrl, Word.class);
			log.info("Consumption of resource was successful!");
			log.info(word.toString());
		} catch (Exception e) {
			log.error("Consumption of resource was unsuccessful :(");
		}
	}
	
	public static void post() {
		// POST REQUEST OPERATION
		RestTemplate restTemplate = new RestTemplate();
		String postRequestUrl = "http://192.168.60.95:8084/word/headword";
		Word newWord = new Word("headword1", "significance1", "kind1");
		try {
			Word addedWord = restTemplate.postForObject(
					postRequestUrl, newWord, Word.class);
			log.info("Resource POST successful!");
			log.info("'Posted' : " + newWord.toString());
		} catch (Exception e) {
			log.error("Resource POST unsuccessful :(");
		}
		
	}
	
	public static void put() {
		// PUT REQUEST OPERATION
		RestTemplate restTemplate = new RestTemplate();
		String putRequestUrl = "";
		
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("headword1", "replacedheadword");
	    Word result = restTemplate.getForObject(putRequestUrl, Word.class, params);
	}
	
	public static void delete() {
		// DELETE REQUEST OPERATION
		RestTemplate restTemplate = new RestTemplate();
		String deleteRequestUrl = "";
		restTemplate.delete(deleteRequestUrl);
	}
	
	public static void getOne() {
		// GET ONE OPERATION
		String getRequestUrl = "";
		
		RestTemplate restTemplate = new RestTemplate();
		
		try { 
			Word word = restTemplate.getForObject(getRequestUrl, Word.class);
			log.info("Consumption of resource was successful!");
			log.info(word.toString());
		} catch (Exception e) {
			log.error("Consumption of resource was unsuccessful :(");
		}
	}
	


	public static void main(String[] args) {
//		post();
		getAll();


		


	}

}
