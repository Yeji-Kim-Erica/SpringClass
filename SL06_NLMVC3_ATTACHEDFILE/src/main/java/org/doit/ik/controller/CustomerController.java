package org.doit.ik.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항 관련
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;

	// 첨부파일 다운로드
	@GetMapping("/download.htm")
	public void download(@RequestParam("dir") String p,
						 @RequestParam("filesrc") String f,
						 HttpServletRequest request,
						 HttpServletResponse response) throws IOException {
		
      String fname =  f;        
      response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));
      String fullPath = request.getServletContext().getRealPath(   p + "/" + fname);

      FileInputStream fin = new FileInputStream(fullPath);
      ServletOutputStream sout = response.getOutputStream(); // 응답 스트림
      byte[] buf = new byte[1024];
      int size = 0;
      while((size = fin.read(buf, 0, 1024)) != -1) {
         sout.write(buf, 0, size); 
      }
      fin.close();
      sout.close();
	}
	
	
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
	/* 첨부파일 업로드하면서 글쓰기 : 내 코드
	@PostMapping("/noticeReg.htm")
	public String noticeReg(NoticeVO notice,
			MultipartFile attach,
			RedirectAttributes rttr) throws Exception {
		
		if (!attach.isEmpty()) {			
			String originalFileName = attach.getOriginalFilename();
			int dotIndex = originalFileName.lastIndexOf(".");
			String fileName = originalFileName.substring(0, dotIndex);
			String fileExtension = originalFileName.substring(dotIndex);
			String parent = "C:\\upload";
			File dest = new File(parent, originalFileName);
			
			// 중복되는 파일 이름이 있는지 검사
			String fileFullName = originalFileName;
			int count = 1;
			while (dest.exists()) {
				fileFullName = String.format("%s(%d)%s", fileName, count, fileExtension);
				dest = new File(parent, fileFullName);
				count++;
			}
			
			// 바뀐 파일명대로 저장
			try {
				attach.transferTo(dest);
				notice.setFilesrc(fileFullName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} // if
		
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount > 0) {
			rttr.addFlashAttribute("result", "Registered");
			return "redirect:notice.htm";
		} else {
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:noticeReg.htm";
		}
	}
	*/
	
	@PostMapping("/noticeReg.htm")
	public String noticeReg(NoticeVO notice, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			// System.out.println("> uploadRealPath : \n" + uploadRealPath);
			String originalFileName = multipartFile.getOriginalFilename();
			
			String filesystemName = getFileNameCheck(uploadRealPath, originalFileName);
			File dest = new File(uploadRealPath, filesystemName );
			try {
				multipartFile.transferTo(dest);
				notice.setFilesrc(filesystemName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} // if
		
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount > 0) {
			rttr.addFlashAttribute("result", "Registered");
			return "redirect:notice.htm";
		} else {
			rttr.addFlashAttribute("result", "Failed");
			return "redirect:noticeReg.htm";
		}
	}
	
	// 파일 이름 중복검사 메서드
	private String getFileNameCheck(String uploadRealPath, String originalFileName) {
		int dotIndex = originalFileName.lastIndexOf(".");
		String fileName = originalFileName.substring(0, dotIndex);
		String fileExtension = originalFileName.substring(dotIndex);
		File dest = new File(uploadRealPath, originalFileName);
		
		String fileFullName = originalFileName;
		int index = 1;
		while (dest.exists()) {
			fileFullName = String.format("%s(%d)%s", fileName, index, fileExtension);
			dest = new File(uploadRealPath, fileFullName);
			index++;
		}
		return fileFullName;
	}

	// 글 수정하기 페이지 : GET
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(String seq, Model model) throws Exception {
		model.addAttribute("notice", this.noticeDao.getNotice(seq));
		return "noticeEdit.jsp";
	}
	// 글 수정하기 페이지 : POST
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice,
						     @RequestParam("o_filesrc") String ofilesrc,
						     HttpServletRequest request,
							 RedirectAttributes rttr) throws Exception {
		
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		
		// 첨부파일 수정		
		if (!multipartFile.isEmpty()) { // 1. 첨부파일이 있을 때
			// 기존 첨부파일 삭제
			File delFile = new File(uploadRealPath, ofilesrc);
	         if (delFile.exists() && delFile.isFile()) {
	            delFile.delete();
	        } // if
	        
	        // 새로 첨부된 파일 저장
	        String originalFilename = multipartFile.getOriginalFilename();
	        String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
	        File dest = new File(uploadRealPath, filesystemName );
	        multipartFile.transferTo(dest);
	        notice.setFilesrc(filesystemName);
			
		} else { // 2. 첨부파일이 없을 때
			notice.setFilesrc(ofilesrc);
		}
				
		// DB 수정
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
	public String noticeDelete(String seq, String filesrc, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		// 1. 첨부파일 있는지 확인 후 파일 삭제
		if (filesrc != null && !filesrc.isEmpty()) {
			String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			File delFilesrc = new File(uploadRealPath, filesrc);
			if (delFilesrc.exists() && delFilesrc.isFile()) {
				delFilesrc.delete();
			}
		}
		
		// 2. DB 삭제
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
