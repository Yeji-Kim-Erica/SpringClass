 package org.doit.ik;

import org.doit.ik.domain.EmpDTO;
import org.doit.ik.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
public class EmpRestController {
	
	private EmpService empService;
	
	@PostMapping(value = "/emp/chkId", produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> chkId(@RequestBody EmpDTO emp) {
		log.info("> EmpRestController.chkId()... POST");
		return this.empService.findEmpno(emp)
			  ? new ResponseEntity<String>("이미 존재하는 아이디입니다.", HttpStatus.OK)
			  : new ResponseEntity<String>("중복확인 완료", HttpStatus.OK);
	}
	
}
