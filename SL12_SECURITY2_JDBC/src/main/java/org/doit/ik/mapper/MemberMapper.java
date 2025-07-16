package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.MemberVO;
 
public interface MemberMapper {
	
	//@Select("SELECT * FROM MEMBER WHERE id = #{id}")
	public MemberVO getMember(@Param("id") String id) throws ClassNotFoundException, SQLException;
	 
	//@Insert()
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
 
}
