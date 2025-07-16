package org.doit.ik.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/common/*")
public class CommonController {

	@GetMapping("/accessError.htm")
	public String accessDenied(Model model, Authentication auth) {
		log.info("> CommonController.accessDenied()... GET");
		model.addAttribute("msg", "접근 금지됨");
		
		// 아래 내용과 매치되는 tiles.xml 내용이 없으므로
		// default로 resolve
		// 요청 URL : /WEB-INF/views/common/accessError.jsp (생성)
		return "/common/accessError";
	}
	
}
