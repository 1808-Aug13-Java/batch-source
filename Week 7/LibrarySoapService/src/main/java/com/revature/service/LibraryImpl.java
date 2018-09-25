package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.exceptions.LibraryFullException;
import com.revature.models.Book;
@WebService(endpointInterface="com.revature.service.Library")
public class LibraryImpl implements Library{
	private List<Book> bookList= new ArrayList<>();
	public LibraryImpl() {
		bookList.add(new Book("Ready, Player 1","Ernest Cline",2015));
		bookList.add(new Book("Revenge of the Sith", "Matthew Stover", 2005));
		bookList.add(new Book("Hunger Games", "Susanne Collins", 2008));
		bookList.add(new Book ("It Devours", "Joseph Phink", 2018));
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return this.bookList;
	}

	@Override
	public String addBook(Book b) throws LibraryFullException  {
		if(this.bookList.size()>3) {
			throw new LibraryFullException("Library full. Cannot add "+b.getName());
		} else {
			this.bookList.add(b);
			return b.getName()+" added to the library";	
		}
	
	}

}
