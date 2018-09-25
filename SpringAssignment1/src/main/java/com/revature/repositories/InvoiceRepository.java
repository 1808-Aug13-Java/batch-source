package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.InvoiceHibernate;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceHibernate, Long>{
	
}
