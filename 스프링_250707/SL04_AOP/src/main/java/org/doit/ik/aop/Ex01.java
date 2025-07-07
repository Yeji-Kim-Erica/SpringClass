package org.doit.ik.aop;

public class Ex01 {

	public static void main(String[] args) {
		
		/*
		 	p.204 [스프링 AOP]
		 	
		 	예) 게시판 글쓰기
		 		공통 기능 + 핵심 비즈니스 로직 기능
		 		 ㄴ 인증		ㄴ 글수정, 글삭제
		 		 ㄴ 권한		
		 		공통 관심사항					 핵심 관심사항
		 cross-cutting concern				core concern
		 
		 	예) 게시판 글수정/삭제
		 	
		 	실습
		 		1) org.doit.ik.aop 패키지 + 스프링 AOP X
		 			ㄴ Calculator.java 인터페이스
		 			ㄴ CalculatorImpl.java implements Calculator
		 			ㄴ 
		 	
		 */
		
		Calculator calc = new CalculatorImpl();
		
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mul(4, 2));
		System.out.println(calc.div(4, 2));
		
		System.out.println("END");
		
	}
	
}
