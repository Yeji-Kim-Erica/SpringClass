package org.doit.ik.di.test;

import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		
		// p.42(44) 스프링을 사용해서 객체 조립/사용하기 (스프링 설정 만들기)
		// p.59(61) ApplicationContext 종류 및 설명
		
		// ApplicationContext = 공장
		
		// 스프링 컨테이너 : 객체를 생성하고 조립하는 공장
		// 컨테이너를 생성하기 위해서 설정정보 xml 파일과는 다른 별도의 xml 파일이 필요함
		
		/* 성적 정보를 입력 받아서 출력하기
		 
		 	1. org.doit.ik.di 패키지
		 		ㄴ Spring Bean Configuration File 선택 → application-context.xml 생성
		 		- application-context.xml의 Namespaces 항목을 클릭하면 사용하고 싶은 내용을 체크만 해도 자동으로 코드가 추가된다.
				- application-context.xml : 스프링의 설정 파일, 스프링 Bean(객체)을 생성하고 조립할 때 사용할 설정 정보를 담은 XML 파일
		 	
		 	2. Ex02.java에서 공장 생성 : new GenericApplicationContext(resourceLocations);
		 
		 */
		
		/* [xml 파일에서 처리된 내용 : 해당 내용은 Spring Container에서 처리됨]
		 
			A객체
			RecordImpl record = new RecordImpl();
			
			B객체 : RecordViewImpl rvi
			B객체에게 A객체를 주입(Injection)
			생성자를 사용해서 주입
			RecordViewImpl rvi = new RecordViewImpl(record);
			
		*/
		
		String[] resourceLocations = {"classpath:org/doit/ik/di/application-context.xml"}; // 용도별로 xml 파일을 나눠서 만드는 경우 배열 활용
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations); // Spring Container 생성
		
		// RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi");
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class); // ctx라는 스프링 컨테이너에서 Bean 객체를 가져옴
		
		rvi.input(); // 성적 입력
		rvi.output(); // 성적 출력
		
		System.out.println(" END. ");
		
	}
	
}
