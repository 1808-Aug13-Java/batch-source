package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Faerye;

@Repository
public interface FaeryeRepository extends JpaRepository<Faerye,Long>{
}
