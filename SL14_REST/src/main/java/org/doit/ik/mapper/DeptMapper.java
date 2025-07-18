package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptVO;

public interface DeptMapper {
	
	// 부서 목록 조회
	List<DeptVO> getDeptList();
	
	// 부서 정보 추가
	int insertDept(DeptVO deptVO);
	
	// 부서 정보 조회
	DeptVO getDept(@Param("deptno") int deptno);
	
	// 부서 정보 수정
	int updateDept(DeptVO deptVO);
	
	// 부서 정보 삭제
	int deleteDept(@Param("deptno") int deptno);
	
}
