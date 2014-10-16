package com.realdolmen.bookshop.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.realdolmen.bookshop.domain.Book;

public interface BookDao {
	
	Long storeBook(Book book);
	
	Book getBookById(Long id);
	
	Book getBookTitleByIsbn(String isbn);
	
	List<Book> getBooksByCategory(String category);
	
	List<Book> getAllBooks();

}
