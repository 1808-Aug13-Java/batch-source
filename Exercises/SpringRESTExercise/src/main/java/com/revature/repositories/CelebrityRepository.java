package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Celebrity;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Integer> {
	
}
