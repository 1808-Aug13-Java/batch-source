package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Intern;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long>{

	public Intern findInternByUsername(String username);
	public Intern findInternByEmail(String email);
	public Intern findInternByUsernameAndPassword(String username, String password);

	
}
