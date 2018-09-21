package com.revature.services;

import java.util.List;

import com.revature.models.Celebrity;

public interface CelebrityService {
	public List<Celebrity> findAllCelebrities();
	public Celebrity findCelebrityById(Integer id);
	public Celebrity addCelebrity(Celebrity c);
	public Celebrity updateCelebrity(Celebrity c);
	public Celebrity deleteCelebrity(Celebrity c);
}
