package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.EmpVO;

public interface EmpMapper {
	
	// 사원번호로 사원정보 조회
	EmpVO selectByEmpno(int empno);
	
	// 사원번호 중복체크 (아이디 중복체크)
	Integer idCheck(String empno);
	
	// 모든 사원정보 조회
	List<EmpVO> selectAll();
	
}
