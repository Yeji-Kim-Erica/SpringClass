package org.doit.ik.controller;

import java.util.List;

import org.doit.ik.domain.DeptVO;
import org.doit.ik.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/dept")
@Log4j
public class DeptController {
	
	@Autowired
	private DeptMapper deptMapper;
	
	// 부서목록조회
	@GetMapping
    public ResponseEntity<List<DeptVO>> getList() {
        List<DeptVO> list = deptMapper.getDeptList();

        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(list); // 200 OK with JSON body
        }
    }
	
	// 부서 정보 추가
	@PostMapping
	public int insert(@RequestBody DeptVO deptVO) {
		/* JSON 형식의 파라미터 → DeptVO
			{
				"dname":"QC",
				"loc":"SEOUL"
			}
		*/
		return deptMapper.insertDept(deptVO);
	}
	
	// 부서 정보 조회
	@GetMapping("/{deptno}")
	public ResponseEntity<DeptVO> getDept(@PathVariable("deptno") int deptno) {
		DeptVO dept = deptMapper.getDept(deptno);
		if (dept != null) {
			return ResponseEntity.ok(dept);
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                         .body(null);
		}
	}
	
	// 부서 정보 수정
	@PutMapping("/{deptno}")
	public ResponseEntity<String> update(@PathVariable("deptno") int deptno, @RequestBody DeptVO deptVO) {
		deptVO.setDeptno(deptno);
		int result = this.deptMapper.updateDept(deptVO);
		if (result > 0) {
		    return ResponseEntity.ok("부서 정보가 성공적으로 수정되었습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                         .body("해당 부서번호(" + deptno + ")의 부서를 찾을 수 없습니다.");
		}
	}
	/* 
	PUT /dept/50
	Content-Type: application/json
	
	{
	  "dname": "SALES",
	  "loc": "SEOUL"
	}
	*/
	   
	// 부서 정보 삭제
	@DeleteMapping("/{deptno}")
	public ResponseEntity<String> delete(@PathVariable int deptno) {
		int result = deptMapper.deleteDept(deptno);
		if (result > 0) {
			return ResponseEntity.ok("부서 삭제 성공");
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 부서를 찾을 수 없습니다");
		}
	}

	
}
