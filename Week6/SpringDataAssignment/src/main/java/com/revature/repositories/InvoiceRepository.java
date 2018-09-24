package com.revature.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer>{

	public Invoice findInvoiceById(int id);
	public Invoice findInvoiceByDate(Date date);
	
}
