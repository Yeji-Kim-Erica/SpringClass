package org.doit.ik;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

@RestController // 응답하는 데이터가 json/xml로 돌아옴
public class ScottRestController {
	
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class);
	
	@PostMapping(value = "/scott/dept/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dept) {
		logger.info("> ScottRestController.insertDept() 컨트롤러 메서드 호출됨...");
		
		int rowCount = this.deptMapper.insertDept(dept);
		ResponseEntity<String> result = null;
		if (rowCount > 0) {
			result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@DeleteMapping(value = "/scott/dept/{deptno}")
	public ResponseEntity<String> deleteDept(@PathVariable("deptno") int deptno) {
		logger.info("> ScottRestController.deleteDept() 컨트롤러 메서드 호출됨...");
		
		int rowCount = this.deptMapper.deleteDept(deptno);
		return rowCount > 0
			? new ResponseEntity<String>("SUCCESS", HttpStatus.OK)
			: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
