package com.revature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.beans.Bear;

@Configuration
public class SpringConfig {
	
	
	@Bean
	public Bear bear() {
		return new Bear();
	}
	

}
