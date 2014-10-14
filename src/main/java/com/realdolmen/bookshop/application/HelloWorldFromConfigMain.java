package com.realdolmen.bookshop.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.realdolmen.bookshop.config.ProductionConfig;
import com.realdolmen.bookshop.services.GreetingService;

public class HelloWorldFromConfigMain {
	
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ProductionConfig.class);

		GreetingService greetingService1 = context.getBean("greetingService1", GreetingService.class);
		greetingService1.helloWorld();
		
		GreetingService greetingService2 = context.getBean("greetingService2", GreetingService.class);	
		greetingService2.helloWorld();
		
		// zorgt ervoor dat alle code wordt uitgevoerd bij eindigen
		context.close();
	}

	
}
