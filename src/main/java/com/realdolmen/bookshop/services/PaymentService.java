package com.realdolmen.bookshop.services;

public interface PaymentService {
	
	public String getPaymentProvider();
	
	public boolean validateCreditCard(String cardNumber);

}
