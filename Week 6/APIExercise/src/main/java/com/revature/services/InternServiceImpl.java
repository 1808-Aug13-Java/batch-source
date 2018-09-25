package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Intern;
import com.revature.repositories.InternRepository;

@Service
public class InternServiceImpl implements InternService{

	@Autowired
	InternRepository internRepo;
	
	@Override
	public List<Intern> findAllInterns() {
		return internRepo.findAll();
	}

	@Override
	public Intern findInternById(Long id) {
		return internRepo.getOne(id);
	}

	@Override
	public Intern addIntern(Intern newIntern) {
		return internRepo.save(newIntern);
	}

	@Override
	public Intern updateIntern(Intern user) {
		return internRepo.save(user);
	}

	@Override
	public void deleteIntern(Long id) {
		Intern a= internRepo.getOne(id);
		 internRepo.delete(a);
		
	}

	@Override
	public Intern login(String user, String pass) {
		return internRepo.findInternByUsernameAndPassword(user, pass);
	}

	
	
}
