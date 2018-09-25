package com.revature.services;

import java.util.List;

import com.revature.models.Intern;

public interface InternService {

	public List<Intern> findAllInterns();
	public Intern findInternById(Long id);
	public Intern addIntern(Intern newIntern);
	public Intern updateIntern(Intern user);
	public void deleteIntern(Long id);
	public Intern login(String user, String pass);
	
}
