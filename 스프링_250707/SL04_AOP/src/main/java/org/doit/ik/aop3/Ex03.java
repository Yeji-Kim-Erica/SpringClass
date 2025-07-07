package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		
		/*
		 	p.209(211) [XML 스키마 기반 AOP 퀵 스타트]
		 	1. CalculatorImpl3.java 추가
		 	2. application-context3.xml 추가
		 	3. pom.xml
		 		ㄴ spring-aop
		 		ㄴ aspectjweaver
			      <dependency>
			         <groupId>org.aspectj</groupId>
			         <artifactId>aspectjweaver</artifactId>
			         <version>${org.aspectj-version}</version>
			      </dependency>
		 	
		 	// p.222 [AroudAdvice 만드는 법]
		 	공통 기능을 구현한 
		 	
		 */
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop3/application-context3.xml");
		
		Calculator calc = ctx.getBean("calc3", Calculator.class);
		// Calculator calc = ctx.getBean("calc", Calculator.class);
		
		System.out.println(calc.add(10, 20));
		System.out.println("END");
		
	}
	
}
