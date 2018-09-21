package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Celebrity;
import com.revature.repositories.CelebrityRepository;

@Service
public class CelebrityServiceImpl implements CelebrityService {
	@Autowired
	CelebrityRepository celebRepo;
	
	@Override
	public List<Celebrity> findAllCelebrities() {
		return celebRepo.findAll();
	}

	@Override
	public Celebrity findCelebrityById(Integer id) {
		return celebRepo.getOne(id);
	}

	@Override
	public Celebrity addCelebrity(Celebrity c) {
		return celebRepo.save(c);
	}

	@Override
	public Celebrity updateCelebrity(Celebrity c) {
		return celebRepo.save(c);
	}

	@Override
	public Celebrity deleteCelebrity(Celebrity c) {
		celebRepo.delete(c);
		return c;
	}

}
