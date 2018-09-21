package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Beverage;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Integer>{

}
