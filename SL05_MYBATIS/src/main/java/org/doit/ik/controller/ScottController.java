package org.doit.ik.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScottController {
	
	@Autowired
	private DeptMapper deptMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);

	@RequestMapping(value = "/scott/dept")
	public String dept(HttpServletRequest request) {
		logger.info("> ScottController.dept() 컨트롤러 메서드 호출됨...");
		
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		request.setAttribute("list", list);
		
		return "scott/dept";
	}
	
}
