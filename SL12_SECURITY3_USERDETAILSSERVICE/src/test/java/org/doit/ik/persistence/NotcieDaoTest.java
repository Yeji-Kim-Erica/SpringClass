package org.doit.ik.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/root-context.xml")
public class NotcieDaoTest {
	
	@Autowired  
	 private NoticeMapper noticeDao;

	@Test
	public void insertTest() { 
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("두 번째 게시글");
		noticeVO.setContent("두 번째 게시글");
		
		int rowCount = 0;
		try {
			rowCount = noticeDao.insert(noticeVO);
			System.out.println( rowCount );
		} catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
		
		System.out.println("INSERT END.");
		
	}

}
