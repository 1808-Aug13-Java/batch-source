package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Dragon;
import com.revature.repositories.DragonRepository;

@Service
public class DragonServiceImpl  implements DragonService{

	@Autowired
	DragonRepository dragonRepo;
	
	@Override
	public List<Dragon> getDragons() {
		return dragonRepo.findAll();
	}

	@Override
	public Dragon getDragonById(Long id) {
		return dragonRepo.getOne(id);
	}

	@Override
	public Dragon createDragon(Dragon dragon) {
		return dragonRepo.save(dragon);
	}

	@Override
	public Dragon updateDragon(Dragon dragon) {
		return dragonRepo.save(dragon);
	}

	@Override
	public Dragon deleteDragon(Dragon dragon) {
		dragonRepo.delete(dragon);
		return dragon;
	}

}
