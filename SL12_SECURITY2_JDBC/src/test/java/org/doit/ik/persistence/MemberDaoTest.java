package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/root-context.xml")
public class MemberDaoTest {
	
	// Caused by: java.io.FileNotFoundException: 
	// class path resource [WEB-INF/spring/root-context.xml]
	// cannot be opened because it does not exist
	
	@Autowired
	private MemberMapper memberDao;
	 
	
	@Test
	public void getMemberTest() { 
	 
		try {
		   MemberVO member = this.memberDao.getMember("kenik");
			System.out.println( member.toString() );
		} catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
		
		System.out.println(" END.");
		
	}
	/*
	@Test
	public void insertTest() {
		
		// MemberDao memberDao = new MemberDao();
		
		MemberVO member = new MemberVO();
		member.setId("kenik"); 
		member.setPwd("1234");  
		member.setName("홍길동"); 
		member.setGender("남성"); 
		member.setBirth("2000-12-21");   
		member.setIs_lunar("Solar");  
		member.setCphone("010-1234-1234");  
		member.setEmail("hong@name.com"); 
		member.setHabit("music,movie"); 
		
		int rowCount = 0;
		try {
			rowCount = memberDao.insert(member);
			System.out.println( rowCount );
		} catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
		
		System.out.println("INSERT END.");
		
	}
	*/

}
