package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {

	ArrayList<DeptDTO> selectDept();
	
	// 부서추가 추상 메서드
	int insertDept(DeptDTO dept);

	// 부서삭제 추상 메서드
	int deleteDept(int deptno);
	
}
