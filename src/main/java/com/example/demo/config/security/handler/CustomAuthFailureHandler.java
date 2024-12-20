package com.example.demo.config.security.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		//로그인 실패 원인에 따른 에러 메시지 설정하기
		String errorMessage;
		
		if (exception.getMessage().contains("user")) {
			errorMessage = "존재하지 않는 아이디입니다.";
		} else if (exception.getMessage().contains("passord")) {
			errorMessage = "비밀번호가 일치하지 않습니다.";
		} else {
			errorMessage = "로그인에 실패했습니다. 다시 시도해주세요.";
		}
		
		//에러 메시지 세션에 저장하기
		request.getSession().setAttribute("loginError", errorMessage);

		System.out.println("ss2"+errorMessage);
		//로그인 페이지로 다시 리다이렉트
		//response.sendRedirect("/secure/login?error=true");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("{\"error\": \"Unauthorized\"}");
	}

}
