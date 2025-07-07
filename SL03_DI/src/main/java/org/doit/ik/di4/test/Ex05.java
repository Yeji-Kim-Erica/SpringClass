package org.doit.ik.di4.test;

import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		
		// p.115(117) 컴포넌트 스캔을 이용한 빈 자동 등록
		
		String[] resourceLocations = {"classpath:org/doit/ik/di4/application-context.xml"}; // 용도별로 xml 파일을 나눠서 만드는 경우 배열 활용
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations); // Spring Container 생성
		
		// RecordViewImpl4 rvi = ctx.getBean("recordViewImpl4", RecordViewImpl4.class); // ctx라는 스프링 컨테이너에서 Bean 객체를 가져옴
		RecordViewImpl4 rvi = ctx.getBean("rvi", RecordViewImpl4.class);
		
		rvi.input(); // 성적 입력
		rvi.output(); // 성적 출력
		
		System.out.println(" END. ");
		
	}
	
}
