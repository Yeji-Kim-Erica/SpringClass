package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	// @Autowired
	// private DataSourceTransactionManager transactionManager;
	
	// @Autowired
	// private TransactionTemplate transactionTemplate;
   
    // 공지사항의 갯수 반환하는 메서드
	@Override
	public int getCount(String field, String query) {
		String sql = " SELECT COUNT(*) CNT "
				   + " FROM NOTICES "
				   + " WHERE " + field + " LIKE :query";
		// MapSqlParameterSource
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("query", query);
		
		return this.npJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
	}
	
    // 공지사항의 목록을 List컬렉션으로 반환하는 메서드
	@Override
	public List<NoticeVO> getNotices(int page, String field, String query){
		int srow = 1 + (page - 1) * 15;
		int erow = 15 + (page - 1) * 15;

		String sql = " SELECT * "
				+ "  FROM ( " 
				+ "                 SELECT ROWNUM NUM, N.* " 
				+ "                 FROM ("
				+ "                          SELECT * " 
				+ "                          FROM NOTICES "
				+ "                          WHERE " + field + " LIKE :query " 
				+ "                   ORDER BY REGDATE DESC"
				+ "                ) N" 
				+ "  ) " 
				+ " WHERE NUM BETWEEN :srow AND :erow ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
	    paramMap.put("srow", srow);
	    paramMap.put("erow", erow);
	    
		return this.npJdbcTemplate.query(sql
									   , paramMap
									   , new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}
	
    // 공지사항 삭제하는 메서드
	@Override
	public int delete(String seq) {
		String sql = "DELETE FROM notices "
                   + " WHERE seq = :seq";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	
     // 공지사항 수정하는 메서드
	@Override
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{
		String sql = "UPDATE NOTICES "
	              + " SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc "
	              + " WHERE SEQ=:seq";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	
    // 공지사항 보기
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * "
	              + " FROM NOTICES "
	              + " WHERE SEQ = :seq";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq );
		return this.npJdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}
	
    // 공지사항 추가하는 메서드 + 작성자 포인트 1 증가
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		result += this.npJdbcTemplate.update(sql, paramSource);
		
		// 2. 포인트 1 증가
		sql = " UPDATE MEMBER "
			+ " SET point = point + 1 "
			+ " WHERE id = :id ";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", "admin");
		result += this.npJdbcTemplate.update(sql, paramSource2);
		
		return result;
	}
	/* 공지사항 추가하는 메서드
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	*/
	
	// 트랜잭션 처리 테스트
	/* @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		result += insert(notice);
		notice.setTitle(notice.getTitle() + " (2)");
		result += insert(notice);
		return result;
	}
	*/
	
	/* [5] 선언적 트랜잭션 처리 @Transactional 어노테이션 사용
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		result += this.npJdbcTemplate.update(sql, paramSource);
		
		// 2. 포인트 1 증가
		sql = " UPDATE MEMBER "
			+ " SET point = point + 1 "
			+ " WHERE id = :id ";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", id);
		result += this.npJdbcTemplate.update(sql, paramSource2);
		
		return result;
	}
	*/
	
	/* [4] 선언적 트랜잭션 처리 <tx:advice> XML 파일 설정
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		result += this.npJdbcTemplate.update(sql, paramSource);
		
		// 2. 포인트 1 증가
		sql = " UPDATE MEMBER "
			+ " SET point = point + 1 "
			+ " WHERE id = :id ";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", id);
		result += this.npJdbcTemplate.update(sql, paramSource2);
		
		return result;
	}
	*/
	
	// 트랜잭션 테스트를 위한 추상메서드 추가
	// [3] transactionTemplate을 사용한 트랜잭션 처리
	/* [3-2] transactionTemplate을 사용한 트랜잭션 처리 : TransactionCallback<Integer>
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql1 = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		
		// 2. 포인트 1 증가
		String sql2 = " UPDATE MEMBER "
				+ " SET point = point + 1 "
				+ " WHERE id = :id ";
		
		result = this.transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus status) {
				int rowCount = 0;
				// 1. 공지사항 쓰기
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
				rowCount += npJdbcTemplate.update(sql1, paramSource);
				
				// 2. 포인트 1 증가
				MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
				paramSource2.addValue("id", id);
				rowCount += npJdbcTemplate.update(sql2, paramSource2);
				
				return rowCount;
			}
		});
		
		return result;
	}
	*/
	
	/* [3-1] transactionTemplate을 사용한 트랜잭션 처리 : TransactionCallbackWithoutResult
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {

		// 1. 공지사항 쓰기
		String sql1 = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		
		// 2. 포인트 1 증가
		String sql2 = " UPDATE MEMBER "
				+ " SET point = point + 1 "
				+ " WHERE id = :id ";
		
		// p.515 예제 코딩 참고
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// 1. 공지사항 쓰기
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
				npJdbcTemplate.update(sql1, paramSource);
				
				// 2. 포인트 1 증가
				MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
				paramSource2.addValue("id", id);
				npJdbcTemplate.update(sql2, paramSource2);
			}
		});
	}
	*/
	/* [2] transactionManager를 사용한 트랜잭션 처리
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql1 = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		
		// 2. 포인트 1 증가
		String sql2 = " UPDATE MEMBER "
					+ " SET point = point + 1 "
					+ " WHERE id = :id ";
		
		// 트랜잭션 설정 정보를 담는 인터페이스 : 기본설정 (DefaultTransactionDefinition)
		//	ㄴ 전파 방식, 격리 레벨 등등
		TransactionDefinition definition = new DefaultTransactionDefinition();
		// 트랜잭션 관리자.getTransaction(설정정보 객체)
		TransactionStatus status = this.transactionManager.getTransaction(definition);
		
		try {
			
			// 1. 공지사항 쓰기
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
			result += this.npJdbcTemplate.update(sql1, paramSource);
			
			// 2. 포인트 1 증가
			MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
			paramSource2.addValue("id", id);
			result += this.npJdbcTemplate.update(sql2, paramSource2);
			
			this.transactionManager.commit(status);
			
		} catch (Exception e) {
			this.transactionManager.rollback(status);
			result = 0;
		}
		
		return result;
	}
	*/
	/* [1] 트랜잭션 처리가 되지 않은 메서드
	@Override
	public int insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
	            + " VALUES( "
	            + "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		result += this.npJdbcTemplate.update(sql, paramSource);
		
		// 2. 포인트 1 증가
		sql = " UPDATE MEMBER "
			+ " SET point = point + 1 "
			+ " WHERE id = :id ";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", id);
		result += this.npJdbcTemplate.update(sql, paramSource2);
		
		return result;
	}
	*/
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT) // DBMS(오라클)에서 지원하는 격리 수준
	public void hitUp(String seq) throws ClassNotFoundException, SQLException {
		String sql = " UPDATE notices "
					+ " SET hit = hit + 1 "
					+ " WHERE seq = :seq ";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		this.npJdbcTemplate.update(sql, paramSource);
	}

	@Override
	@Transactional
	public int getHit(String seq) throws ClassNotFoundException, SQLException {
		String sql = " SELECT hit "
				+ "	FROM notices "
				+ " WHERE seq = :seq ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seq", seq);
		return this.npJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}
}
