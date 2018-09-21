package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Bear;
import com.revature.repositories.BearRepository;

@Service
public class BearServiceImpl implements BearService {

	@Autowired
	BearRepository bearRepo;

	public List<Bear> findAllBears() {
		return bearRepo.findAll();
	}

	public Bear findBearById(Long id) {
		return bearRepo.getOne(id);
	}

	public Bear addBear(Bear newBear) {
		return bearRepo.save(newBear);
	}

	public Bear updateBear(Bear bear) {
		return bearRepo.save(bear);
	}

	public Bear deleteBear(Bear bear) {
		bearRepo.delete(bear);
		return bear;
	}
	
	
}