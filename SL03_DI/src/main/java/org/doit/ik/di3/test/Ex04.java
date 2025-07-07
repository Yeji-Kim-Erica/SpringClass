package org.doit.ik.di3.test;

import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		
		// p.103(105) 어노테이션을 이용한 객체 간 의존 자동 연결
		
		String[] resourceLocations = {"classpath:org/doit/ik/di3/application-context.xml"}; // 용도별로 xml 파일을 나눠서 만드는 경우 배열 활용
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations); // Spring Container 생성
		
		RecordViewImpl3 rvi = ctx.getBean("rvi", RecordViewImpl3.class); // ctx라는 스프링 컨테이너에서 Bean 객체를 가져옴
		
		rvi.input(); // 성적 입력
		rvi.output(); // 성적 출력
		
		System.out.println(" END. ");
		
	}
	
}
