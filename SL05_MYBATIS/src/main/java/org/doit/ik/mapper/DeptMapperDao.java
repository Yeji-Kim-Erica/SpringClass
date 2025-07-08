package org.doit.ik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.doit.ik.domain.DeptDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// @Repository
public class DeptMapperDao implements DeptMapper {

	@Autowired(required = false)
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public ArrayList<DeptDTO> selectDept() {
		// sqlSessionTemplate 객체로 구현
		List<DeptDTO> list = this.sqlSessionTemplate.selectList("org.doit.ik.mapper.DeptMapper");
		return new ArrayList<>(list);
	}

	@Override
	public int insertDept(DeptDTO dept) {
		return 0;
	}

	@Override
	public int deleteDept(int deptno) {
		return 0;
	}

}
