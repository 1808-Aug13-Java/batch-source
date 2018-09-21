package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Faerye;
import com.revature.repositories.FaeryeRepository;

@Service
public class FaeryeServiceImpl implements FaeryeService {

	@Autowired
	FaeryeRepository faeryeRepo;

	@Override
	public List<Faerye> findAllFaeryes() {
		return faeryeRepo.findAll();
	}
	@Override
	public Faerye findFaeryeById(Long id) {
		return faeryeRepo.getOne(id);
	}
	@Override
	public Faerye addFaerye(Faerye newFaerye) {
		return faeryeRepo.save(newFaerye);
	}
	@Override
	public Faerye updateFaerye(Faerye faerye) {
		return faeryeRepo.save(faerye);
	}
	@Override
	public Faerye deleteFaerye(Faerye faerye) {
		faeryeRepo.delete(faerye);
		return faerye;
	}
}
