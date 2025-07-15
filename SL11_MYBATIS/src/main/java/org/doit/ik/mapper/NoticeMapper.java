package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.NoticeVO;

// @Transactional(propagation = Propagation.REQUIRED)
public interface NoticeMapper {
   
    // 공지사항 개수 반환
	public int getCount(@Param("field") String field, @Param("query") String query);
	
    // 공지사항의 목록을 List컬렉션으로 반환
	public List<NoticeVO> getNotices(@Param("page") int page, @Param("field") String field, @Param("query") String query);
	
    // 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
    // 공지사항 수정
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
    // 공지사항 보기
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
	
    // 공지사항 추가
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// 트랜잭션 테스트를 위한 추상메서드 추가 -> 인터페이스 이동 (MemberShipService로)
	// public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
	
	// 조회수 증가
	public void hitUp(String seq) throws ClassNotFoundException, SQLException;
	
	// 조회수 조회
	public int getHit(String seq) throws ClassNotFoundException, SQLException;
	
}
