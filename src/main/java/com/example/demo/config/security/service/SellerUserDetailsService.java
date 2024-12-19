//package com.example.demo.config.security.service;
//
//import java.util.Collections;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entity.Member;
//import com.example.demo.repository.MemberRepository;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Service
//@RequiredArgsConstructor
//public class SellerUserDetailsService implements UserDetailsService {
//	
//	private final MemberRepository memberRepository;
//	
//	//사용자 이름(ID)으로 사용자 정보를 가져오는 메서드
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		//1. DB에서 판매자 정보를 찾음
//		System.out.println("공연 관리자 아이디: "+username);
//		Member member = memberRepository.findByIdAndRole(username, "SELLER")
//				.orElseThrow(() -> new UsernameNotFoundException("공연 관리자를 찾을 수 없습니다: " + username));
//		
//		//2. 찾은 관리자 정보를 Spring Security가 이해할 수 있는 형태(UserDetails)로 변환
//		return User.builder()
//				.username(member.getId())
//				.password(member.getPassword())
//			    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER")))
//				.build();
//	}
//}
