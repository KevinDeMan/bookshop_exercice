package com.realdolmen.bookshop.services;

import org.springframework.stereotype.Component;

public class PaymentServiceImpl implements PaymentService{
	
	private String paymentProvider;
	
	public void setPaymentProvider(String paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	@Override
	public String getPaymentProvider() {
		return paymentProvider;
	}
	
	@Override
	public boolean validateCreditCard(String cardNumber) {
		// just some error check ... not a real implementation
		if( cardNumber.startsWith("4506")) {
			return true;
		} else {
			return false;
		}
	}
}
