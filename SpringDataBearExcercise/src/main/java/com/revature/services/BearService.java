package com.revature.services;

import java.util.List;

import com.revature.models.Bear;

public interface BearService {

	public List<Bear> findAllBears();
	public Bear findBearById(Long id);
	public Bear addBear(Bear newBear);
	public Bear updateBear(Bear bear);
	public Long deleteBear(Long id);
	
}