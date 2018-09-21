package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Bear;

@Repository
public interface BearRepository extends JpaRepository<Bear, Long> {
	
	public Bear findBearByName(String name);
	public Bear findBearById(Long id);
	
}