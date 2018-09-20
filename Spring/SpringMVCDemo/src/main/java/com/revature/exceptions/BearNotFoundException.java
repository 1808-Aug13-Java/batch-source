package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.I_AM_A_TEAPOT, reason="That's a teapot not a bear")
public class BearNotFoundException extends RuntimeException{

	public BearNotFoundException() {
		super();
	}
	
}
