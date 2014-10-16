package com.realdolmen.bookshop.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.realdolmen.bookshop.config.ProductionConfig;
import com.realdolmen.bookshop.dao.BookDao;
import com.realdolmen.bookshop.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@ContextConfiguration(classes={ProductionConfig.class})
@Transactional
public class BookServiceImplTest {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	@Qualifier("BookDaoSqlImpl")
	BookDao bookDao;
	
	private List<Book> books = new ArrayList<>();

	@Before
	@Transactional
	public void setUp() throws Exception {
		books.add(new Book("The Godfather", "978-0099528128", "Mafia"));
		books.add(new Book("The Sicilian", "978-0099580799", "Mafia"));
		books.add(new Book("The Last Don", "978-0099580789", "Mafia"));
		books.add(new Book("First Blah", "978-0099580789", "Rubbish"));
		for( Book book : books ) {
			bookDao.storeBook(book);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMustFindTheGodfather() {
		Book book = bookService.getBookTitleByIsbn("978-0099528128");
		assertNotNull(book);
	}
	
	@Test
	public void testMustNotFindAlCapone() {
		Book book =  bookService.getBookTitleByIsbn("978-1492936084");
		assertNull(book);
	}
	
	@Test 
	public void testBookStoreHasName() {
		assertNotNull(bookService.getBookStoreName());
	}
	
	@Test
	public void testValidCreditCard() {
		assertTrue("Card starting with '4506' is valid", bookService.validateCreditCard("450612151318465153648"));
	}
	
	@Test
	public void testInvalidCreditCard() {
		assertFalse("Card not starting with '4506' is considdered invalid", bookService.validateCreditCard("123456156115151"));
	}
	
	@Test
	public void testAccountNumber() {
		assertNotNull(bookService.getVatNumber());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncorrectIsbn() {
		bookService.getBookTitleByIsbn("123");
	}
	
	@Test
	public void testMultiplecalls() {
		bookService.validateCreditCard("450612151318465153648");
		bookService.validateCreditCard("450612151318465153648");
		bookService.validateCreditCard("450612151318465153648");
		bookService.validateCreditCard("450612151318465153648");
		bookService.validateCreditCard("450612151318465153648");
		bookService.validateCreditCard("450612151318465153648");
		bookService.getBookStoreName();
	}
	
	@Test
	public void testCanFindAllBooks() {
		assertNotNull( bookService.getAllBooks() );
	}
	
	@Test
	public void canFindBooksByCategory_IfCategoryExists() {
		String category = "Mafia";
		List<Book> retreivedBooks = bookService.getBooksByCategory(category);
		for( Book book : books) {
			if( book.getGenre().equalsIgnoreCase(category)) {
				assertTrue(book.getTitle() + " must be part of found books in category " + category, retreivedBooks.contains(book));
			} else {
				assertFalse(book.getTitle() + " must not be found in the category " + category, retreivedBooks.contains(book));
			}	
		}
	}

}
