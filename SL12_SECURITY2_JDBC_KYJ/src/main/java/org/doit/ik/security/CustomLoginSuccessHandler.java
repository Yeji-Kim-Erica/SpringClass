package org.doit.ik.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

// 로그인이 성공했을 때 실행할 작업에 관한 클래스
@Component("customLoginSuccessHandler")
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.warn("🎉🎉🎉 로그인 성공! CustomLoginSuccessHandler.onAuthenticationSuccess()...");
		
		// 인증 사용자가 가지고 있는 ROLE == 권한
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(auth -> {
			roleNames.add(auth.getAuthority());
		});
		
		log.warn("😎😎😎 Role Names : " + roleNames);
		
		if (roleNames.contains("ROLE_ADMIN") ) {
			response.sendRedirect("/");
			return;
		} else if ( roleNames.contains("ROLE_MANAGER") ) {
			response.sendRedirect("/customer/notice.htm");
			return;
		} else if ( roleNames.contains("ROLE_USER") ) {
			response.sendRedirect("/customer/notice.htm");
			return;
		}
	}
	
}
