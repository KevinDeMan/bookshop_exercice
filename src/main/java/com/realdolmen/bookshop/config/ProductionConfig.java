package com.realdolmen.bookshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.realdolmen.bookshop.services.GreetingServiceImpl;

@Configuration
@ComponentScan(basePackages={"com.realdolmen.bookshop"})
public class ProductionConfig {
	
	@Value("Hello, world from config java file")
	private String message1;
	
	@Value("Welcome to spring")
	private String message2;
	
	@Bean
	public GreetingServiceImpl greetingService1() {
		return new GreetingServiceImpl(message1);
	}
	
	@Bean
	public GreetingServiceImpl greetingService2() {
		return new GreetingServiceImpl(message2);
	}

}
