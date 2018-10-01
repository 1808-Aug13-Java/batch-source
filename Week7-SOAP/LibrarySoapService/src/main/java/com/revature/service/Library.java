package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.execeptions.LibraryFullException;
import com.revature.models.Book;

@WebService
public interface Library {
// this is going to be our Service Endpoint Interface
	
	public List<Book> getAllBooks();
	public String addBook(Book book) throws LibraryFullException;
	
}
