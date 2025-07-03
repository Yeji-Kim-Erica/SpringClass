package org.doit.ik.controller;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("list")
	public void list(Model model) {
		log.info("> BoardController.list()... GET");
		model.addAttribute("board", this.boardService.getList());
	}
	
	@GetMapping("register")
	public void register() {
		log.info("> BoardController.register()... GET");
	}
	
	@PostMapping("register")
	public String register(BoardVO boardVO
						 	, RedirectAttributes redirectAttributes) {
		log.info("> BoardController.register()... POST");
		this.boardService.register(boardVO);
		
		// redirectAttributes.addAttribute("result", 4); // 쿼리 스트링에 달고 감 (경로)
		// redirectAttributes.addFlashAttribute("result", 5); // 세션 1회성 값
		redirectAttributes.addFlashAttribute("result", boardVO.getBno());
		
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
	public void modify(Long bno, Model model) {
		log.info("> BoardController.get/modify()... GET");
		model.addAttribute("boardVO", this.boardService.get(bno));
	}
	
	// BoardVO boardVO : 커맨드 객체
	@PostMapping("modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr) {
		log.info("> BoardController.modify()... POST");
		if (this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "SUCCESS");
		}
		
		// [1]
		// return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		
		// [2]
		rttr.addAttribute("bno", boardVO.getBno());
		return "redirect:/board/get";
	}
	
	@GetMapping("remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		log.info("> BoardController.remove()... GET");
		if (this.boardService.remove(bno)) {
			rttr.addFlashAttribute("remove", bno);
		}
		return "redirect:/board/list";
	}
	
}
