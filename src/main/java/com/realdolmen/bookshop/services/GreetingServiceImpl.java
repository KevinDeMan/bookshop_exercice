package com.realdolmen.bookshop.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public class GreetingServiceImpl implements GreetingService{
	private String message;
	
	public GreetingServiceImpl() {
		super();
	}
	
	public GreetingServiceImpl(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	@Override
	public void saySomething() {
		System.out.println( message );
	}

}
