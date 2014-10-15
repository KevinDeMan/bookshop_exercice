package com.realdolmen.bookshop.convertor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class VatNumberConvertorTest {
	
	private VatNumberConverter converter;
	
	private final String VALID_NUMBER_WITHOUT_FORMAT = "BE123456789012";
	private final String VALID_NUMBER_WITH_FORMAT = "BE123.456789.012";
	private final String INVALID_NUMBER_COUNTRY = "FR123456789012";
	private final String INVALID_NUMBER_TO_SHORT = "BE123456";
	private final String INVALID_NUMBER_NOT_NUMERIC = "BELGIE123456";
	
	@Before
	public void setUp(){
		converter = new VatNumberConverter();
	}
	
	@Test
	public void testValidVatNumber() {
		assertNotNull(converter.convert(VALID_NUMBER_WITHOUT_FORMAT));
		assertNotNull(converter.convert(VALID_NUMBER_WITH_FORMAT));
	}
	

}
