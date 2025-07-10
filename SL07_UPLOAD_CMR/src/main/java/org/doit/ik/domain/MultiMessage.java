package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class MultiMessage {

	private String output;
	
	// <input type="file" name="attach" multiple="multiple">
	// [1]
	// private CommonsMultipartFile[] attach;
	// [2]
	private List<CommonsMultipartFile> attach;
	
}
