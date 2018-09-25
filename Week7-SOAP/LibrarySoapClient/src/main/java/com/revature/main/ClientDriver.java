package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Book;
import com.revature.service.Library;

public class ClientDriver {

	public static void main(String[] args) {
		
		String serviceUrl = "http://localhost:8082/LibrarySoapService/Library";
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Library.class); //provide our interface
		factory.setAddress("http://localhost:8082/LibrarySoapService/Library");
		
		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		Library library = (Library) factory.create();
		List<Book> books = library.getAllBooks();
		for(Book b: books) {
			System.out.println(b);
		}
		
		Book book = new Book("Song of Solomon","Toni Morrison", 1992);
		System.out.println(library.addBook(book));

	}

}
