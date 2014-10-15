package com.realdolmen.bookshop.services;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realdolmen.bookshop.domain.VatNumber;

public interface BookService {
	
	public String getBookStoreName();
	
	public Set<String> getManagers();
	
	public String getBookTitleByIsbn(String isbn) throws IllegalArgumentException;
	
	public boolean validateCreditCard(String creditCard);
	
	public void setVatNumber(VatNumber vatNumber);
	
	public VatNumber getVatNumber();

}
