package org.doit.ik.controller;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService boardService;
	
/*	[1] 게시판 리스트 (페이징 처리 X)
	@GetMapping("list")
	public void list(Model model) {
		log.info("> BoardController.list()... GET");
		model.addAttribute("board", this.boardService.getList());
	}
*/
	// [2] 게시판 리스트 (페이징 처리 O)
	@GetMapping("list")
	public void list(Model model, Criteria criteria) {
		log.info("> BoardController.list()... GET");
		model.addAttribute("board", this.boardService.getListWithPaging(criteria));
		
		int endPage = (int)(Math.ceil((double)this.boardService.getTotal(criteria)/criteria.getAmount()));
		if (criteria.getPageNum() > endPage) {
			criteria.setPageNum(endPage == 0? 1:endPage);
		}
		
		// 페이징 블럭
		model.addAttribute("pageMaker", new PageDTO(criteria, this.boardService.getTotal(criteria)));
		// list.jsp로 포워딩
	}
	
	@GetMapping("register")
	public void register() {
		log.info("> BoardController.register()... GET");
	}
	
	@PostMapping("register")
	public String register(BoardVO boardVO
						 	, RedirectAttributes rttr
						 	, Criteria criteria) {
		log.info("> BoardController.register()... POST");
		this.boardService.register(boardVO);
		
		// redirectAttributes.addAttribute("result", 4); // 쿼리 스트링에 달고 감 (경로)
		// redirectAttributes.addFlashAttribute("result", 5); // 세션 1회성 값
		rttr.addFlashAttribute("result", "REGISTERED");
		rttr.addFlashAttribute("bno", boardVO.getBno());
		rttr.addAttribute("pageNum", 1);
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		
		// 리다이렉트 원할 시, redirect: 를 앞에 붙이면 됨
		// return "redirect:/board/list";
		// return "redirect:/board/list?success=ok";
		return "redirect:/board/list";
	}
	
	// [1] /board/get?bno=${board.bno}
/*	@GetMapping("get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("> BoardController.get()... GET");
		model.addAttribute("boardVO", this.boardService.get(bno));
	}
*/	
	// [2] /board/get/${board.bno}
/*	@GetMapping("get/{bno}")
	public String get(@PathVariable("bno") Long bno, Model model) {
		log.info("> BoardController.get()... GET");
		model.addAttribute("boardVO", this.boardService.get(bno));
		
		return "/board/get";
	}
*/	
	@GetMapping(value = {"get", "modify"})
	public void get(Long bno, Model model
						, @ModelAttribute Criteria criteria) {
		log.info("> BoardController.get/modify()... GET");
		model.addAttribute("boardVO", this.boardService.get(bno));
		// model.addAttribute("criteria", criteria);
	}
	
	// BoardVO boardVO : 커맨드 객체
	@PostMapping("modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr
							, Criteria criteria) {
		log.info("> BoardController.modify()... POST");
		if (this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "MODIFIED");
		}
		
		// [1]
		// return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		
		// [2]
		/*
		rttr.addAttribute("bno", boardVO.getBno());
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:/board/get";
		*/
		
		// [3] QueryString 활용
		return String.format("redirect:/board/get%s&bno=%d", criteria.getListLink(), boardVO.getBno());
	}
	
	@GetMapping("remove")
	public String remove(Long bno, RedirectAttributes rttr
							, @ModelAttribute Criteria criteria) {
		log.info("> BoardController.remove()... GET");
		if (this.boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "REMOVED");
			rttr.addFlashAttribute("bno", bno);
			
			int endPage = (int)(Math.ceil((double)this.boardService.getTotal(criteria)/criteria.getAmount()));
			if (criteria.getPageNum() > endPage) {
				criteria.setPageNum(endPage == 0? 1:endPage);
			}

			/*
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			*/
		}
		// return "redirect:/board/list";
		return String.format("redirect:/board/list%s", criteria.getListLink());
	}
	
}
