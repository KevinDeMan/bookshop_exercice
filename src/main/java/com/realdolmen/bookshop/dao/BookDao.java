package com.realdolmen.bookshop.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

public interface BookDao {
	
	public String getBookTitleByIsbn(String isbn);

}
