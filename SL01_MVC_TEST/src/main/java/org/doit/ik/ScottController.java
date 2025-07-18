package org.doit.ik;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
public class ScottController {
	
	// DI
	// @Autowired
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);
	
	// 컨트롤러 메서드 선언
	@RequestMapping(value = "/scott/dept")
	public String dept(HttpServletRequest request) {
		logger.info("> ScottController.dept() 컨트롤러 메서드 호출됨...");
		
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		request.setAttribute("list", list);
		
		return "scott/dept";
	}
	
	@Autowired
	private EmpMapper empMapper;
	
	// @RequestMapping(value = "/scott/emp", method = RequestMethod.POST)
	@PostMapping(value = "/scott/emp")
	public String emp(HttpServletRequest request
					  , @RequestParam("deptno") int[] deptnoArr) { // [2]
		logger.info("> ScottController.emp() 컨트롤러 메서드 호출됨...");
		
		// [1] JSP에서 MVC 패턴으로 처리하면 사용하는 방식
/*		int[] deptnoArr = null;
		String[] pDeptnoArr = request.getParameterValues("deptno");
		deptnoArr = new int[pDeptnoArr.length];
		for (int i = 0; i < pDeptnoArr.length; i++) {
			deptnoArr[i] = Integer.parseInt(pDeptnoArr[i]);
		}
*/
		
		// ArrayList<EmpDTO> list = this.empMapper.selectEmp();
		ArrayList<EmpDTO> list = this.empMapper.selectEmp(deptnoArr);
		request.setAttribute("list", list);
		
		return "scott/emp";
	}
	
	@GetMapping(value = "/scott/empdept")
	public String empdept(HttpServletRequest request
						  , @RequestParam(value = "deptno", defaultValue = "10") int deptno) {
		logger.info("> ScottController.empdept() 컨트롤러 메서드 호출됨...");
		
		// [1]
		ArrayList<DeptDTO> dlist = this.deptMapper.selectDept();
		request.setAttribute("dlist", dlist);
		
		// [2]
		ArrayList<EmpDTO> elist = this.empMapper.selectEmpDept(deptno);
		request.setAttribute("elist", elist);
		
		return "scott/empdept";
	}
	
}
