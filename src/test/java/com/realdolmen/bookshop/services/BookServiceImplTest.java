package com.realdolmen.bookshop.services;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.realdolmen.bookshop.config.ProductionConfig;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@ContextConfiguration(classes={ProductionConfig.class})
public class BookServiceImplTest {
	
	@Autowired
	BookService bookService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMustFindTheGodfather() {
		String title = bookService.getBookTitleByIsbn("978-0099528128");
		assertEquals("Book must be 'The Godfather'", "The Godfather", title);
	}
	
	@Test
	public void testMustNotFindAlCapone() {
		String title = bookService.getBookTitleByIsbn("978-1492936084");
		assertNull(title);
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


}
