package com.realdolmen.bookshop.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realdolmen.bookshop.dao.BookDao;
import com.realdolmen.bookshop.dao.BookDaoStubbedImpl;
import com.realdolmen.bookshop.domain.Book;
import com.realdolmen.bookshop.domain.VatNumber;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	private String bookStoreName;
	private Set<String> managers;
	private int maxBookCount;
	private VatNumber vatNumber;
	
	@Autowired
	@Qualifier("BookDaoSqlImpl")
	BookDao bookDao ;
	
	@Autowired
	PaymentService paymentService;
	
	public BookServiceImpl() {
		
	}
	
	public BookServiceImpl(String bookStoreName, int maxBookCount) {
		super();
		this.bookStoreName = bookStoreName;
		this.maxBookCount = maxBookCount;
	}
	
	public void setMaxBookCount(int maxBookCount) {
		this.maxBookCount = maxBookCount;
	}

	@Override
	public String getBookStoreName() {
		return bookStoreName;
	}

	@Override
	public Set<String> getManagers() {
		return managers;
	}

	@Override
	public Book getBookTitleByIsbn(String isbn) throws IllegalArgumentException {
		if( isbn.length() < 10 ) {
			throw new IllegalArgumentException("Oh oh, you made a typo!");
		}
		return bookDao.getBookTitleByIsbn(isbn);
	}

	@Override
	public boolean validateCreditCard(String creditCard) {
		return paymentService.validateCreditCard(creditCard);
	}

	@Override
	public void setVatNumber(VatNumber vatNumber) {
		// how do I get convertor kick in over here?
		this.vatNumber = vatNumber;
	}

	@Override
	public VatNumber getVatNumber() {
		return vatNumber;
	}

	@Override
	public void addBookToStore(Book book) {
		bookDao.storeBook(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		return bookDao.getBooksByCategory(category);
	}

}
