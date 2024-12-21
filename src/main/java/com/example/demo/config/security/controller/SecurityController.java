package com.example.demo.config.security.controller;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.NoticeDAO;
import com.example.demo.dto.NoticeDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationManager authenticationManager;

    private final MemberRepository MemberRepository;
    
    private final NoticeDAO NoticeDAO;
    // 로그인 페이지 보여주기
    @GetMapping("/secure/login")
    public String login() {
        return "login";  // login.html 템플릿 반환
    }
    
    // 로그인 페이지 보여주기
    @GetMapping("/test")
    @ResponseBody
    public List<NoticeDTO> test() {

    	
        return NoticeDAO.getNoticeList(); // login.html 템플릿 반환
    }
    
    
    // 로그인 페이지 보여주기
    @GetMapping("/test2")
    @ResponseBody
    public List<Member> test2() {
    	List<Member> member = MemberRepository.findAll();
        return member; // login.html 템플릿 반환
    }
    
    /*
    // 관리자 로그인 처리
    @PostMapping("/api/secure/login/admin")
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> adminLogin(@RequestBody LoginDTO loginDTO) {
        try {
            // 인증 토큰 생성
            UsernamePasswordAuthenticationToken token = 
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            // 인증 시도
            Authentication authentication = authenticationManager.authenticate(token);

            // 인증 성공시 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new ApiResponse<>(200, "로그인 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, "로그인 실패", null));
        }
    }
	*/
    // 판매자 로그인 처리
//    @PostMapping("/api/secure/login/seller")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<Void>> sellerLogin(@RequestBody LoginDTO loginDTO) {
//        try {
//            UsernamePasswordAuthenticationToken token = 
//                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return ResponseEntity.ok(new ApiResponse<>(200, "로그인 성공", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ApiResponse<>(401, "로그인 실패", null));
//        }
//    }

    /*
    // 로그아웃 처리
    @PostMapping("/api/secure/logout")
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
        SecurityContextHolder.clearContext();  // SecurityContext 초기화
        session.invalidate();  // 세션 무효화

        return ResponseEntity.ok(new ApiResponse<>(200, "로그아웃 성공", null));
    }
    */
}

//package com.example.demo.config.security.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.demo.config.security.dto.LoginDTO;
//import com.example.demo.util.ApiResponse;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class SecurityController {
//
//    private final AuthenticationManager authenticationManager;
//
//    // 로그인 페이지 보여주기
//    @GetMapping("/secure/login")
//    //public String loginPage() {
//    public String login() {
//    	
//        return "login";  // login.html 템플릿 반환
//    }
//
//    /*
//    // 관리자 로그인 처리
//    @PostMapping("/api/secure/login/admin")
//    public ResponseEntity<ApiResponse<Void>> adminLogin(@RequestBody LoginDTO loginDTO) {
//        try {
//            // 인증 토큰 생성
//            UsernamePasswordAuthenticationToken token = 
//                new UsernamePasswordAuthenticationToken(loginDTO.getId(), loginDTO.getPassword());
//
//            // 인증 시도
//            Authentication authentication = authenticationManager.authenticate(token);
//
//            // 인증 성공시 SecurityContext에 저장
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.ok(new ApiResponse<>(200, "로그인 성공", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ApiResponse<>(401, "로그인 실패", null));
//        }
//    }
//	*/
//    // 판매자 로그인 처리
//    @PostMapping("/api/secure/login/seller")
//    public ResponseEntity<ApiResponse<Void>> sellerLogin(@RequestBody LoginDTO loginDTO) {
//        try {
//            UsernamePasswordAuthenticationToken token = 
//                new UsernamePasswordAuthenticationToken(loginDTO.getId(), loginDTO.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return ResponseEntity.ok(new ApiResponse<>(200, "로그인 성공", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ApiResponse<>(401, "로그인 실패", null));
//        }
//    }
//
//    // 로그아웃 처리
//    @PostMapping("/logout")
//    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
//        SecurityContextHolder.clearContext();  // SecurityContext 초기화
//        session.invalidate();  // 세션 무효화
//
//        return ResponseEntity.ok(new ApiResponse<>(200, "로그아웃 성공", null));
//    }
//}