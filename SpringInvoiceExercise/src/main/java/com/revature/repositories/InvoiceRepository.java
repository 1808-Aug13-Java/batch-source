package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>
{

}
