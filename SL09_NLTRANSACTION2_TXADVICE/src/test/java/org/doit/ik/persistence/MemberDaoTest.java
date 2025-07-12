package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/root-context.xml")
public class MemberDaoTest {
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void selectTest() {
		try {
			MemberVO member = memberDao.getMember("dpwl1003");
			System.out.println(member.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	@Test
	public void insertTest() {
		
		// MemberDao memberDao = new MemberDao();
		
		MemberVO member = new MemberVO();
		member.setId("dpwl1003");
		member.setPwd("1234");
		member.setName("김예지");
		member.setGender("여성");
		member.setBirth("2000-10-03");
		member.setIs_lunar("Solar");
		member.setCphone("010-5560-1211");
		member.setEmail("dpwl1003@naver.com");
		member.setHabit("music, movie");
		
		int rowcount = 0;
		try {
			rowcount = memberDao.insert(member);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(rowcount);
		System.out.println("INSERT END. ");
		
	}
	*/
}
