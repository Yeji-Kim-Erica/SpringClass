package org.doit.ik.controller;

import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항 관련
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;

	// 컨트롤러 안의 메서드 -> 컨트롤러 메서드 p.356
	// 공지사항 목록 요청 URL 
	// http://localhost/customer/notice.htm?page=2&field=검색조건&query=검색어
	// @RequestMapping(value =".notice.htm", method = RequestMethod.GET )
	/* [1]
	@GetMapping("/notice.htm") // 위와 같은 코딩
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// 리턴자료형 : ModelAndView  p282
		ModelAndView mav = new ModelAndView();

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		// ?  or ?page=
		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		mav.addObject("list", list);
		mav.addObject("message", "Hello World!");

		mav.setViewName("notice.jsp");

		return mav;
	}
	*/
	/* [2]
	@GetMapping("/notice.htm")
	public String notices(@RequestParam(required = false) Map<String, String> allParams, Model model) throws Exception {
		String ppage = allParams.get("page");
		String pfield = allParams.get("field");
		String pquery = allParams.get("query");
		
		int page = 1;
		String field = "title";
		String query = "";
		
		// ?  or ?page=
		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");
		
		return "notice.jsp";
	}
	*/
	// [3]
	@GetMapping(value = "/notice.htm")
	public String notices(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
						  @RequestParam(value = "field", required = false, defaultValue = "title") String field,
						  @RequestParam(value = "query", required = false, defaultValue = "") String query,
						  Model model) throws Exception {

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");

		return "notice.jsp";
	}
	
	// 공지사항 목록 요청 URL
	// http://localhost/customer/noticeDetail.htm?seq=1
	// [1]
	/*
	@GetMapping("/noticeDetail.htm")
	   public ModelAndView noticeDetail(
	         HttpServletRequest request
	         , HttpServletResponse response) throws Exception {
	      // 리턴자료형 : ModelAndView  p282
	      ModelAndView mav = new ModelAndView("noticeDetail.jsp");      
	      String seq = request.getParameter("seq");       
	      NoticeVO notice  = this.noticeDao.getNotice(seq);      
	      mav.addObject("notice", notice);          
	      return mav;
	   }
	*/
	// [2]
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(@RequestParam("seq") String seq, Model model) throws Exception {
		NoticeVO notice  = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeDetail.jsp";
	}
	
	// 글쓰기 페이지 : GET
	@GetMapping("/noticeReg.htm")
	public String noticeReg() {
		return "noticeReg.jsp";
	}
	// 글쓰기 페이지 : POST
	@PostMapping("/noticeReg.htm")
	public String noticeReg(NoticeVO notice,
							RedirectAttributes rttr) throws Exception {
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount > 0) {
			rttr.addFlashAttribute("result", "Registered");
			return "redirect:notice.htm";
		} else {
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:noticeReg.htm";
		}
	}
	
	// 글 수정하기 페이지 : GET
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(String seq, Model model) throws Exception {
		model.addAttribute("notice", this.noticeDao.getNotice(seq));
		return "noticeEdit.jsp";
	}
	// 글 수정하기 페이지 : POST
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice, RedirectAttributes rttr) throws Exception {
		int rowCount = this.noticeDao.update(notice);
		
		if (rowCount > 0) {
			rttr.addAttribute("seq", notice.getSeq());
			rttr.addFlashAttribute("result", "Edited");
			return "redirect:noticeDetail.htm";
		} else {
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:noticeEdit.htm";
		}
	}
	
	// 글 삭제하기
	@GetMapping("/noticeDel.htm")
	public String noticeDelete(String seq, RedirectAttributes rttr) throws Exception {
		int rowCount = this.noticeDao.delete(seq);
		if (rowCount > 0) {
			rttr.addFlashAttribute("result", "Deleted");
			return "redirect:notice.htm";
		} else {
			rttr.addFlashAttribute("result", "Failed");
			rttr.addAttribute("seq", seq);
			return "redirect:noticeDetail.htm";
		}
	}

} // class
