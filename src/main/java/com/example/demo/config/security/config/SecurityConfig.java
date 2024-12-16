package com.example.demo.config.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.config.security.handler.CustomAuthFailureHandler;
import com.example.demo.config.security.handler.CustomAuthSuccessHandler;
import com.example.demo.config.security.service.AdminUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity        // Spring Security 활성화
public class SecurityConfig {

	@Autowired
	private AdminUserDetailsService adminUserDetailsService;
	
	@Autowired
	private CustomAuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private CustomAuthFailureHandler authFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// CRSF 보호기능 비활성화 -> API 서버에서는 보호 필요없음.
			.csrf(crsf -> crsf.disable()) 
			.userDetailsService(adminUserDetailsService)
			//HTTP 요청에 대한 권한 설정
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/secure/login", "/static/**", "/api/secure/login/**" , "/api/secure/test").permitAll() //로그인 페이지는 모두 접근 가능
					//.requestMatchers("/secure/admin/**").hasRole("ADMIN") // admin 경로 ADMIN 역할 사용자만 접근 가능
					//.requestMatchers("/secure/seller/**").hasRole("SELLER") // seller 경로는 SELLER 역할 사용자만 접근 가능
					.anyRequest().authenticated(); // 그 외 요청은 인증된 사용자만 접근 가능
			})	
			// 로그인 설정
			.formLogin(form ->{
				// 사용자 정의 로그인 페이지 URL 설정
				form.loginPage("/secure/login")
					.loginProcessingUrl("/api/secure/login/admin") // 로그인 처리 URL
					.successHandler(authSuccessHandler)
					.failureHandler(authFailureHandler);
			})
			// 로그아웃 설정
			.logout(logout ->{
				logout.logoutUrl("/api/secure/logout")
					  .logoutSuccessUrl("/secure/login")
					  .deleteCookies("JSESSIONID")
					  .invalidateHttpSession(true);
			})
			// 세션 관리 설정
			.sessionManagement(session -> {
				session.maximumSessions(10) //동시 세션 하나만 허용! 다른 세션 생성되면 자동 로그아웃 처리!
					// 세션 만료시 이동할 URL 설정
					.expiredUrl("/secure/login");
			});
		
		return http.build();
	}
}
