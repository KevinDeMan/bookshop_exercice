package com.realdolmen.bookshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import com.realdolmen.bookshop.domain.VatNumber;
import com.realdolmen.bookshop.services.BookService;
import com.realdolmen.bookshop.services.BookServiceImpl;
import com.realdolmen.bookshop.services.GreetingServiceImpl;
import com.realdolmen.bookshop.services.PaymentService;
import com.realdolmen.bookshop.services.PaymentServiceImpl;

@Configuration
@ComponentScan(basePackages={"com.realdolmen.bookshop"})
@EnableAspectJAutoProxy
@ImportResource({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductionConfig {
	
	@Value("Hello, world from config java file")
	private String message1;
	
	@Value("Welcome to spring")
	private String message2;
	
	@Value("An offer you cannot refuse")
	private String bookStoreName;
	
	@Value("100")
	private int maxBooks;
	
	@Value("BE.123.4567890.12")
	private VatNumber vatNumber;
	
	@Bean
	public GreetingServiceImpl greetingService1() {
		return new GreetingServiceImpl(message1);
	}
	
	@Bean
	public GreetingServiceImpl greetingService2() {
		return new GreetingServiceImpl(message2);
	}
	
	@Bean
	public BookServiceImpl bookService() {
		BookServiceImpl bookService = new BookServiceImpl(bookStoreName, maxBooks );
		bookService.setVatNumber(vatNumber);
		return bookService;
	}
	
	@Bean
	public PaymentServiceImpl paymentService() {
		PaymentServiceImpl paymentService = new PaymentServiceImpl();
		paymentService.setPaymentProvider("Visa");
		return paymentService;
	}

}
