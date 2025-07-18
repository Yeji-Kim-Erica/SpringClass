package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper; 
import org.doit.ik.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항 
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeMapper noticeDao; 
	
	@Autowired
	private MemberShipService memberShipService; 
	
	@GetMapping("/download.htm")
	public void download(
			@RequestParam("dir") String p  , 
			@RequestParam("file") String f ,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
        String fname =  f;  		
		response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));
		String fullPath = request.getServletContext().getRealPath(	p + "/" + fname);

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
	
	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			  @RequestParam("seq") String seq
			, @RequestParam("filesrc") String filesrc
			, HttpServletRequest request
			, RedirectAttributes rttr
			, Model model
			
			, @RequestParam("writer") String writer
			, Principal principal
			) throws ClassNotFoundException, SQLException {
		// 시작   - UI처리가 되어있기에 필요없지만 개념 이해..
		String loginUsername = principal.getName();
		boolean isOwner = writer.equals(loginUsername);
	    boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
	    
	    if (!isOwner && !isAdmin) {
	        rttr.addFlashAttribute("error", "삭제 권한이 없습니다.");
	        return "redirect:noticeDetail.htm?seq="+seq+"&error=del"; // 또는 적절한 에러 페이지
	    }
	    // 끝
		
		// 2. 첨부파일 확인 후 파일 삭제 코딩.
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFilesrc = new File(uploadRealPath, filesrc);
		if( delFilesrc.exists() && delFilesrc.isFile() ) delFilesrc.delete();
		
		// 1.  DB 삭제
		int rowCount = this.noticeDao.delete(seq); 
		rttr.addFlashAttribute("result", rowCount);
		return "redirect:notice.htm";
		
		// rttr.addAttribute("seq", seq);
		// return "redirect:noticeDetail.htm";
	}
	
	// [2] 첨부파일 처리 추가
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO notice
			,@RequestParam("o_filesrc") String ofilesrc
			, HttpServletRequest request  
			, RedirectAttributes rttr
			, Model model) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		// 1. 
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		if ( !multipartFile.isEmpty() ) {  // 수정 - 새로운 첨부파일 선택
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			// ㄱ 이전 첨부된 파일은 삭제. 
			File delFile = new File(uploadRealPath, ofilesrc);
			if (  delFile.exists()  && delFile.isFile()  ) {
				delFile.delete();
			} // if
			
			// ㄴ 새로 첨부된 파일은 저장.
			String  originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest); // 실제 파일 저장
			notice.setFilesrc(filesystemName);
			
		} else {  // 새로운 첨부파일 선택하지 않은 경우
			notice.setFilesrc(ofilesrc);
		} // if
		
		// 2.
		int rowCount = this.noticeDao.update(notice);
		rttr.addFlashAttribute("result", rowCount);
		// rttr.addFlashAttribute("seq", notice.getSeq());   일회성
		// noticeDetail.htm?seq=3
		rttr.addAttribute("seq", notice.getSeq());
		return "redirect:noticeDetail.htm";
	} 
	
	// <a class="btn-edit button" href="noticeEdit.htm?seq=${ notice.seq }">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(
			@RequestParam("seq") String seq
			, Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "customer.noticeEdit";
	}

	// <form action="" method="post">
	// [3]                                                  a.txt
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;		
		while( true ) {			
			File f = new File(uploadRealPath, originalFilename);			
			if( !f.exists() ) return originalFilename;	
			// a
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 );
			// .txt
			String ext =  originalFilename.substring(originalFilename.length() - 4 );
			//                        a-2.txt  
			originalFilename = fileName+"-"+(index)+ext;
			index++;
		} // while 
	}
	// [3] 
	//@PreAuthorize("isAuthenticated()")
	@PostMapping( value =  "/noticeReg.htm" )
	public String noticeReg( 
			NoticeVO notice 
			, HttpServletRequest request
			, Principal principal
			// , @AuthenticationPrincipal UserDetails user
			) 
			throws ClassNotFoundException, SQLException, IllegalStateException, IOException {   // 커맨드객체	 
		
		CommonsMultipartFile multipartFile = notice.getFile();		
		String uploadRealPath = null;
		
		if ( !multipartFile.isEmpty() ) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			String  originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);    // a-1.txt 저장
			notice.setFilesrc(filesystemName);
		} // if 
		notice.setWriter( principal.getName() ); 
		//int rowCount = this.noticeDao.insert(notice);
		int rowCount = 1;
		this.memberShipService.insertAndPointUpOfMember(notice, "kenik");
		if (rowCount == 1) { 
			return "redirect:notice.htm";
		} else { 
			return "redirect:noticeReg.htm?error";
		} // if	 
	} 

	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping("/noticeReg.htm" )
	public String noticeReg(HttpSession session) {
		// return "noticeReg.jsp";
		return "customer.noticeReg";  // ViewName
	}

	// [2]
	// http://localhost/customer/noticeDetail.htm?seq=1 
	@GetMapping("/noticeDetail.htm" )	
	public String noticeDetail(
			@RequestParam("seq") String seq
			, Model model) throws Exception {
		this.noticeDao.hitUp(seq); // 조회수 증가.
		NoticeVO  notice  = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);		
		//return "noticeDetail.jsp";
		return "customer.noticeDetail";  // ViewName
	} 

	// [3]
	@GetMapping(value = "/notice.htm")
	public String notices(
			@RequestParam(value = "page", defaultValue = "1") int page , 
			@RequestParam(value = "field", defaultValue = "title") String field,
			@RequestParam(value = "query", defaultValue = "") String query,
			Model model) throws Exception {		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");
		return "customer.notice";
	} 

} // class
