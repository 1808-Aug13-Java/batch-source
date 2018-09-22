package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.revature.models.Word;
import com.revature.controllers.WordController;
import com.revature.services.WordServiceImpl;
@SpringBootApplication
public class Driver {

	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
	}

}
