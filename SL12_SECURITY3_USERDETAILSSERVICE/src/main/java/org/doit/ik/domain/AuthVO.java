package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO { // 권한 정보를 가지고 있는 클래스

	private String username;
	private String authority;
	
}
