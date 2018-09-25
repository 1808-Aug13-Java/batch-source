package com.revature;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;
import com.revature.services.InvoiceServiceImpl;

@SpringBootApplication
public class Driver {

	public static void main(String[] args) {

		SpringApplication.run(Driver.class, args);
//		InvoiceService ins = new InvoiceServiceImpl();
//		Invoice inv= new Invoice();
//		inv.setAmount(89);
//		inv.setDate(Date.valueOf("2018-01-02"));
//		ins.addInvoice(inv);
	}

}
