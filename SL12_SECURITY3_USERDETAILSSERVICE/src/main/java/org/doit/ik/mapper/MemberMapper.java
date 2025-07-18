package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;
 
public interface MemberMapper {
	
	//@Select("SELECT * FROM MEMBER WHERE id = #{id}")
	public MemberVO getMember(@Param("id") String id) throws ClassNotFoundException, SQLException;
	 
	//@Insert()
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
	// 회원 정보 + 권한 정보
	public MemberVO read(@Param("userid") String id) throws ClassNotFoundException, SQLException;
 
}
