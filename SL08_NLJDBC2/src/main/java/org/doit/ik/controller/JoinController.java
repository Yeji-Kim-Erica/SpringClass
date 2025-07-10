package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/joinus/*")
public class JoinController {

	@Autowired
	private MemberDao memberDao;
	
	// 회원가입 페이지
	@GetMapping("join.htm")
	public String join() {
		return "join.jsp";
	}
	
	@PostMapping("join.htm")
	public String join(MemberVO member, RedirectAttributes rttr) throws Exception {
		int rowCount = this.memberDao.insert(member);
		
		if (rowCount > 0) { // 회원가입 성공
			rttr.addFlashAttribute("result", "Registered");
			return "redirect:login.htm";
			
		} else { // 회원가입 실패
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:join.htm";
		}
	}
	
	// 로그인 페이지
	@GetMapping("login.htm")
	public String login() {
		return "login.jsp";
	}
	
	@PostMapping("login.htm")
	public String login(MemberVO member, RedirectAttributes rttr) throws Exception {
		boolean isCorrectPwd = member.getPwd().equals(this.memberDao.getMember(member.getId()).getPwd());
		
		if (isCorrectPwd) { // 로그인 성공
			rttr.addFlashAttribute("result", "Logined");
			return "redirect:/index.htm";
			
		} else { // 로그인 실패
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:login.htm";
		}
	}
}
