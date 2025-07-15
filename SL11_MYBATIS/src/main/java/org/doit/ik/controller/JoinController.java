package org.doit.ik.controller;

import java.beans.PropertyEditorSupport;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/joinus/*")
public class JoinController {

	@Autowired
	private MemberMapper memberDao;
	
	// 회원가입 페이지
	@GetMapping("join.htm")
	public String join() {
		return "joinus.join";
	}
	
	// [2] p334 요청 파라미터의 값 변환처리
	   @InitBinder
	   public void initBinder(WebDataBinder binder) { 
	      
	       binder.registerCustomEditor(String.class, "habit", new PropertyEditorSupport() {
	           @Override
	           public void setAsText(String text) {
	               setValue(text);
	           }

	           @Override
	           public void setValue(Object value) {
	               if (value instanceof String[]) {
	                   String joined = String.join(",", (String[]) value);
	                   super.setValue(joined);
	               } else {
	                   super.setValue(value);
	               }
	           }
	       });
	   }
	
	   @PostMapping("/join.htm")
	   public String join( 
	         MemberVO member
	         , @RequestParam("year") String year
	         , @RequestParam("month") String month
	         , @RequestParam("day") String day 
	         , RedirectAttributes rttr) throws Exception{
	      // 1.
	      String birth = String.format("%s-%s-%s", year, month, day);
	      member.setBirth(birth);
	       
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
		return "joinus.login";
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
