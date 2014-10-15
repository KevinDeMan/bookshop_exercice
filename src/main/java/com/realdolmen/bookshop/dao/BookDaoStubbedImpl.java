package com.realdolmen.bookshop.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoStubbedImpl implements BookDao {
	
	Map<String, String> books;
	
	public BookDaoStubbedImpl() {
		books = new HashMap<>();
		addSomeBooks();
		
	}

	private void addSomeBooks() {
		books.put("978-0099528128", "The Godfather");
		books.put("978-0099580799", "The Sicilian");
		books.put("978-0099580799", "The Last Don");
	}

	@Override
	public String getBookTitleByIsbn(String isbn) {
		return books.get(isbn);
	}

}
