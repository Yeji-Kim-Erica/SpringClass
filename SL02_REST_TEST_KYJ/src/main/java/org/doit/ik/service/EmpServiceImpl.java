package org.doit.ik.service;

import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.EmpMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class EmpServiceImpl implements EmpService {

	private EmpMapper empMapper;
	
	@Override
	public boolean findEmpno(EmpDTO emp) {
		log.info("EmpServiceImpl.findEmpno()...");
		return empMapper.selectEmpno(emp) == 1;
	}
	
}
