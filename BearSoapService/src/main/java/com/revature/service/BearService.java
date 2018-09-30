package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.exceptions.TooManyBearsException;
import com.revature.models.Bear;

@WebService
public interface BearService {
	//this is going to be our Service Endpoint Interface
	
	public List<Bear> getAllBears();
	public String addBear(Bear bear) throws TooManyBearsException;
	public String deleteBear(String name);
}