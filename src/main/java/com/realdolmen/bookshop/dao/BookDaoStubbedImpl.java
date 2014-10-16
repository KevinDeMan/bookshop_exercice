package com.realdolmen.bookshop.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.realdolmen.bookshop.domain.Book;

@Repository(value="BookDaoStubbedImpl")
public class BookDaoStubbedImpl implements BookDao {
	
	Map<Integer, Book> books;
	
	int id = 0;
	
	public BookDaoStubbedImpl() {
		books = new HashMap<>();
		addSomeBooks();
		
	}

	private void addSomeBooks() {
		books.put(1, new Book("The Godfather", "978-0099528128", "Mafia"));
		books.put(2, new Book("The Sicilian", "978-0099580799", "Mafia"));
		books.put(3, new Book("The Last Don", "978-0099580789", "Mafia"));
		id = 4;
	}

	@Override
	public Book getBookTitleByIsbn(String isbn) {
		Book book = null;
		Iterator iterator = books.keySet().iterator();
		while (iterator.hasNext()) {  
		   String key = iterator.next().toString();  
		   Book value = (Book) books.get(key);  
		   if( value.getIsbn().equals(isbn)) {
			   book = value;
			   break;
		   }
		} 
		
		return book;
	}

	@Override
	public Long storeBook(Book book) {
		book.setId(id);
		books.put(id, book);
		return null;
	}

	@Override
	public Book getBookById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
