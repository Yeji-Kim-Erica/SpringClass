package org.doit.ik.service;

import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class EmpServiceImpl implements EmpService{
	
	// @Autowired
	// @Setter
	private EmpMapper empMapper;

	// empdto 가 null 인 경우에는 empno 는 사용 가능 : true 리턴
	@Override
	public boolean isEmpnoAvailable(int empno) { 
		return this.empMapper.selectUserByEmpno(empno) == null;
	}

}
