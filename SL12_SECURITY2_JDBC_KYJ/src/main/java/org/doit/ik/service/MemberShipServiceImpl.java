package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.MemberMapper;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipServiceImpl implements MemberShipService {
	
	@Autowired
	NoticeMapper noticeDao;
	
	@Autowired
	MemberMapper memberMapper;

	// 트랜잭션 처리
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		result += this.noticeDao.insert(notice);
		result += this.memberMapper.pointUp(id);
		return result;
	}

}
