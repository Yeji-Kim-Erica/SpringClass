package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeDaoTest {

	@Autowired
	private NoticeMapper noticeDao;
	
	@Test
	public void insertTest() {
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("두 번째 게시글");
		noticeVO.setContent("두 번째 게시글");
		int rowcount = 0;
		try {
			rowcount = noticeDao.insert(noticeVO);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(rowcount);
		System.out.println("INSERT END. ");
		
	}

}
