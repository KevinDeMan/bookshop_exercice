package com.realdolmen.bookshop.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookServiceExceptionAspect {
	
	@AfterThrowing(pointcut="SystemArchitecture.logMethodExecution()", throwing="ex" )
	public void doSomeErrorStuff(IllegalArgumentException ex) {
		System.err.println("Sleep with the fishes, you damn exception with this message : " + ex.getMessage());
	}
}
