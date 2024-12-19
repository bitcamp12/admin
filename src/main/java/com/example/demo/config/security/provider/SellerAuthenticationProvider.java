//package com.example.demo.config.security.provider;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Component
//@RequiredArgsConstructor
//public class SellerAuthenticationProvider implements AuthenticationProvider {
//	
////	private final SellerUserDetailsService userDetailsService;
//	private final PasswordEncoder passwordEncoder;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//		
//		if (userDetails == null) {
//			throw new BadCredentialsException("사용자를 찾을 수 없습니다.");
//		}
//		
//		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
//		}
//		
//	
//		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//	} 
//	
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
//	}
//}
