package com.realdolmen.bookshop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.realdolmen.bookshop.domain.VatNumber;

@Component
public class VatNumberConverter implements Converter<String, VatNumber> {
	
	private final static String BE = "BE";
	private final static int RAW_LENGTH = 14;
	private final static int NUMERIC_LENGTH = 12;

	@Override
	public VatNumber convert(String text) {
		checkRawTextLength(text);
		String countryCode = checkCountryCodeEqualToBe(text);
		String remainder = getRemainingDigits(text);
		VatNumber vatNumber = createVatNumber(countryCode, remainder);
		
		return vatNumber;
	}

	private VatNumber createVatNumber(String countryCode, String remainder) {
		VatNumber vatNumber = new VatNumber();
		vatNumber.setCountry(countryCode);
		vatNumber.setId(Long.parseLong(remainder));
		return vatNumber;
	}

	private String getRemainingDigits(String text) {
		String remainder = text.substring(2);
		remainder = remainder.replaceAll("\\D+","");
		
		if( remainder.length() != NUMERIC_LENGTH) {
			throw new IllegalArgumentException("Belgian account should have exact " + NUMERIC_LENGTH + "numeric charachters.");
		}
		return remainder;
	}

	private String checkCountryCodeEqualToBe(String text) {
		String countryCode = text.substring(0, 2);
		if( ! countryCode.equalsIgnoreCase(BE)) {
			throw new IllegalArgumentException("Belgian account should start with 'BE'");
		}
		return countryCode;
	}

	private void checkRawTextLength(String text) {
		if( text.length() < RAW_LENGTH ) {
			throw new IllegalArgumentException("Belgian VAT should be at least " + RAW_LENGTH + " chracters long");
		}
	}
	
	
}
