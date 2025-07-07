package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		
		/*
		 	p.226(228) [@Aspect 어노테이션 기반 AOP 퀵 스타트]
		 	1. CalculatorImpl4.java 추가
		 	2. application-context4.xml 추가
		 	3. pom.xml
		 	
		 */
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop4/application-context4.xml");
		
		Calculator calc = ctx.getBean("calc4", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class);
		
		System.out.println(calc.add(10, 20));
		System.out.println("END");
		
	}
	
}
