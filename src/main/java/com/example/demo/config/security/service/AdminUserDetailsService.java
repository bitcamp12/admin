package com.example.demo.config.security.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {
	
	//DB에서 관리자 정보를 가져오는 인터페이스
	private final AdminRepository adminRepository;
	
	//사용자 이름(ID)으로 사용자 정보를 가져오는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//1. DB에서 관리자 정보를 찾음
		System.out.println("ss : "+username);
		Admin admin = adminRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
		
		//2. 찾은 관리자 정보를 Spring Security가 이해할 수 있는 형태(UserDetails)로 변환
		return User.builder()
				.username(admin.getId())
				.password(admin.getPassword())
			    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
				.build();
	}
}
