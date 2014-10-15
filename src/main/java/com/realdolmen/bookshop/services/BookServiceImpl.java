package com.realdolmen.bookshop.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realdolmen.bookshop.dao.BookDao;
import com.realdolmen.bookshop.domain.VatNumber;

public class BookServiceImpl implements BookService {
	
	private String bookStoreName;
	private Set<String> managers;
	private int maxBookCount;
	private VatNumber vatNumber;
	
	@Autowired
	BookDao bookDao;
	
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
	public String getBookTitleByIsbn(String isbn) throws IllegalArgumentException {
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

}
