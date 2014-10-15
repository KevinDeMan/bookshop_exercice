package com.realdolmen.bookshop.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodTimerAspect {
	
	private final static String DIVIDER = " :: ";
	
	private Map<String, Integer> methods = new HashMap<>();

	@Around("SystemArchitecture.logMethodExecution()")
	public Object logMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getCanonicalName();
		String methodName = pjp.getSignature().getName();
		long start = System.nanoTime();
		if(!pjp.getSignature().getName().equals("initBinder")) {
			System.out.println("START OF : " + className + DIVIDER + methodName);
		}
		
		countMethodCalls(methodName);
		
		Object output = pjp.proceed();
		
	    long elapsedTime = System.nanoTime() - start;
	    if(!methodName.equals("initBinder")) {
	    	System.out.println("END OF : " + className + DIVIDER + methodName + DIVIDER + elapsedTime);
	    }
		
		return output;
	}
	
	@PreDestroy
	public void dumpResults() {
		Iterator iterator = methods.keySet().iterator();
		System.out.println("=========== DUMPING METHOD CALLS ============");
		   
		while (iterator.hasNext()) {  
		   String key = iterator.next().toString();  
		   String value = methods.get(key).toString();  
		   System.out.println("\t" + key + " " + value);  
		} 
		
		System.out.println("=============================================");
	}

	private void countMethodCalls(String methodName) {
		if( methods.containsKey(methodName)) {
			methods.put(methodName, methods.get(methodName) + 1);
		} else {
			methods.put(methodName, 1);
		}
	}
}
