package com.example.demo.config.security.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {
	
	//DB에서 관리자 정보를 가져오는 인터페이스
	private final AdminRepository adminRepository;
	
	private final MemberRepository memberRepository;
	
	//사용자 이름(ID)으로 사용자 정보를 가져오는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//1. DB에서 관리자 정보를 찾음
		System.out.println("사이트 관리자 아이디 : "+username);
	
		Optional<Admin> admin = adminRepository.findById(username);
		System.out.println("exist");
		if (admin.isPresent()) {
		//2. 찾은 관리자 정보를 Spring Security가 이해할 수 있는 형태(UserDetails)로 변환
			System.out.println("admin  whswoh exist");
			return User.builder()
					.username(admin.get().getId())
					.password(admin.get().getPassword())
				    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
					.build();
		}

		Optional<Member> seller = memberRepository.findByRoleAndId(Member.Role.SELLER,username);
		
		
		if (seller.isPresent()) {
			System.out.println("seller  whswoh exist");
			
			System.out.println(seller.get().getPassword());
		//2. 찾은 관리자 정보를 Spring Security가 이해할 수 있는 형태(UserDetails)로 변환
			return User.builder()
					.username(seller.get().getId())
					.password(seller.get().getPassword())
				    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER")))
					.build();
		}
		
		return null;
	}
}
