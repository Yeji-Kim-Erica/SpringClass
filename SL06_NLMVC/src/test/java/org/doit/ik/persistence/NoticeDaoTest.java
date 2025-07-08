package org.doit.ik.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeDaoTest {

	// @Autowired
	// private NoticeDao noticeDao;
	
	@Test
	public void insertTest() {
		
		NoticeDao noticeDao = new NoticeDao();
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("두 번째 게시글");
		noticeVO.setContent("두 번째 게시글");
		
		int rowCount = 0;
		try {
			rowCount = noticeDao.insert(noticeVO);
			System.out.println(rowCount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
