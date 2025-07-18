package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.EmpVO;

public interface AjaxMapper {
	
	// 해당 사원 번호의 사원 정보 반환
	List<EmpVO> selectByDeptEmps(@Param("deptno") int deptno);
	
	// 부서원 삭제
	int deleteEmp(@Param("empno") int empno) ;
	
}
