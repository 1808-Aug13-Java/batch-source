package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Invoices;

public interface InvoiceRepository extends JpaRepository<Invoices, Integer>{

}
