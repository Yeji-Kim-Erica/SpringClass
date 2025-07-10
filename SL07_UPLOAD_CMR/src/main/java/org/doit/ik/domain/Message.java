package org.doit.ik.domain;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class Message {

	private String output;
	
	// <input type="file" name="attach">
	// private MultipartFile attach;
	private CommonsMultipartFile attach;
	
}
