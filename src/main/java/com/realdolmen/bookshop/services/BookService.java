package com.realdolmen.bookshop.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realdolmen.bookshop.domain.Book;
import com.realdolmen.bookshop.domain.VatNumber;

public interface BookService {
	
	String getBookStoreName();
	
	Set<String> getManagers();
	
	List<Book> getAllBooks();
	
	List<Book> getBooksByCategory(String category);
	
	Book getBookTitleByIsbn(String isbn) throws IllegalArgumentException;
	
	boolean validateCreditCard(String creditCard);
	
	void setVatNumber(VatNumber vatNumber);
	
	VatNumber getVatNumber();
	
	void addBookToStore(Book book);

}
