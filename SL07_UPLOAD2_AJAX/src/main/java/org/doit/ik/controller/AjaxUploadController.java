package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class AjaxUploadController {

	// ajax/upload
	@GetMapping("/ajax/upload")
	public void ajaxupload() {};
	
}
