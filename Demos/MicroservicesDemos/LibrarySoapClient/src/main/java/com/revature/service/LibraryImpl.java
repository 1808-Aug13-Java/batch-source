package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.models.Book;

@WebService(endpointInterface="com.revature.service.Library")
public class LibraryImpl implements Library {

	private List<Book> booklist = new ArrayList<>();
	
	public LibraryImpl() {
		booklist.add(new Book("Ready Player 1", "Ernest Cline", 2015));
		booklist.add(new Book("Revenge of the Sith", "Matthew Stover", 2005));
		booklist.add(new Book("Hunger Games", "Susanne Collins", 2008));
		booklist.add(new Book("It Devours", "Joseph Phink", 2018));
	}
	
	@Override
	public List<Book> getAllBooks() {
		return this.booklist;
	}

	@Override
	public String addBook(Book book) {
		this.booklist.add(book);
		return book.getName()+" added to the library";
	}
	
}
