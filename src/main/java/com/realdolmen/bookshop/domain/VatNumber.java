package com.realdolmen.bookshop.domain;

public class VatNumber {
	
	private String country;
	private long id;
	
	public VatNumber() {
		
	}
	
	public VatNumber(String country, long id) {
		super();
		this.country = country;
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
