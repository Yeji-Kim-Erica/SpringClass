package org.doit.ik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class EmpController {
	
	@GetMapping("/emp")
	public void emp() {
		log.info("> EmpController.emp() 컨트롤러 메서드 호출됨...");
	}
	
}
