package com.example.demo.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class FirstAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("First advice b4 proceed");
		arg0.proceed();
		System.out.println("First advice after proceed");

		
		return null;
	}

}
