package com.example.demo.config.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component // 로그인 성공시 실행될 동작을 정의하는 인터페이스
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		System.out.println("dddsds");
		// 세션 생성
		HttpSession session = request.getSession();
		
		
		// 인증된 사용자의 정보를 세션에 저장
		session.setAttribute("id", authentication.getName());
		session.setAttribute("role", "ADMIN");
				
		
		//관리자 메인 페이지로 리다이렉트
		response.sendRedirect("/secure/admin/index");		
	}
}
