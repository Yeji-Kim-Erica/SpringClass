package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.NoticeVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// @Transactional(propagation = Propagation.REQUIRED)
public interface NoticeMapper {
	  
	public int getCount(@Param("field") String field, @Param("query") String query) throws ClassNotFoundException, SQLException;
	 
	public List<NoticeVO> getNotices(@Param("page") int page, @Param("field") String field, @Param("query") String query    ) throws ClassNotFoundException, SQLException; 
	 
	// 파라미터 1개 일 경우에는 이름유추가능하다.
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	  
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException; 
	 
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException; 
 
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException;
		
	// 트랜잭션 테스트를 위한 추상메서드 추가
	// [ MemberShipService 인터페이스로 이동.]
	// public void insertAndPointUpOfMember(NoticeVO notice, String id)  throws ClassNotFoundException, SQLException;
	
	public void hitUp(String seq) throws ClassNotFoundException, SQLException ;	
	public int getHit(String seq) throws ClassNotFoundException, SQLException ;
}
