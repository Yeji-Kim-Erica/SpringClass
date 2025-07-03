package org.doit.ik.mapper;

import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {

	int selectEmpno(EmpDTO emp);
	
	EmpDTO selectUserByEmpno(int empno);
	
}
