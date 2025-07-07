package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		
		/*
		 	p.204 [스프링 AOP]
		 	
		 	1. 스프링 API -> 스프링 AOP 구현
		 	
		 */
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop2/application-context.xml");
		
		Calculator calc = ctx.getBean("calcProxy", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class);
		
		System.out.println(calc.add(10, 20));
		System.out.println("END");
		
	}
	
}
