package org.doit.ik.di.test;

import org.doit.ik.di.Record;
import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main (String[] args) {
		// p.40(42) 스프링을 사용하지 않고 객체 조립/사용하기
		
		/* 성적 정보를 입력 받아서 출력하기
		 
		 	1. Record.java	  	   인터페이스
		 	2. RecordImpl.java	   구현 클래스
		 	3. RecordView.java 	   인터페이스
		 	4. RecordViewImpl.java 구현 클래스
		 
		 */
		
		// A객체
		RecordImpl record = new RecordImpl();
		
		 // B객체
		// RecordViewImpl rvi = new RecordViewImpl();
		
		// B객체에게 A객체를 주입(Injection)
		
		// [주입 방법 1] 생성자를 사용해서 주입
		// RecordViewImpl rvi = new RecordViewImpl(record);
		
		// [주입 방법 2] setter를 사용해서 주입
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		
		rvi.input(); // 성적 입력
		rvi.output(); // 성적 출력
		
		System.out.println(" END. ");
		
	}
	
}
