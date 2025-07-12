package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberShipServiceImpl implements MemberShipService {
	
	@Autowired
	NoticeDao noticeDao;

	// 트랜잭션 처리 테스트
	// @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		result += this.noticeDao.insert(notice);
		notice.setTitle(notice.getTitle() + " (2)");
		result += this.noticeDao.insert(notice);
		return result;
	}

}
