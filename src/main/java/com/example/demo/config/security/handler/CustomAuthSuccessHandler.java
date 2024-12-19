package com.example.demo.config.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // 로그인 성공시 실행될 동작을 정의하는 인터페이스
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException {
		
		// Session ->  SecurityContextHolder (보안 컨텍스트)
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
//		
//		// 사용자의 권한 확인
//		String role = authentication.getAuthorities().stream()
//				.findFirst()
//				.get()
//				.getAuthority()
//				.replace("ROLE_", "");
//		
//
//		// 권한에 따라 리다이렉트
//		if ("ADMIN".equals(role)){
//			response.sendRedirect("/secure/admin/index");
//		} else if ("SELLER".equals(role)) {
//			response.sendRedirect("/secure/seller/index");
//		}
	}
}
