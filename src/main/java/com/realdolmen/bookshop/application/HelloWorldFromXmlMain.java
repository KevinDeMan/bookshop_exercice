package com.realdolmen.bookshop.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.realdolmen.bookshop.services.GreetingService;

public class HelloWorldFromXmlMain {

	public static void main(String[] args) {
		String path = "src/main/webapp/WEB-INF/spring/";
		AbstractApplicationContext context = new FileSystemXmlApplicationContext( path + "root-context.xml", path + "appServlet/servlet-context.xml");

		GreetingService greetingService1 = context.getBean("greetingService1", GreetingService.class);
		greetingService1.saySomething();
		
		GreetingService greetingService2 = context.getBean("greetingService2", GreetingService.class);	
		greetingService2.saySomething();
		
		// zorgt ervoor dat alle code wordt uitgevoerd bij eindigen
		context.close();
	}

}
