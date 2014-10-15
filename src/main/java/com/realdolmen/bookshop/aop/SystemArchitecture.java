package com.realdolmen.bookshop.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemArchitecture {
	
	@Pointcut("execution(* com.realdolmen.bookshop.services.*.*(..))")
	public void logMethodExecution() { }
	
}
