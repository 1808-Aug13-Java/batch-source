package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.models.Book;

@WebService
public interface Library {
// this is goint to be our service endpoint interface
	
	public List<Book> getAllBooks();
	public String addBook(Book b);
	
}
