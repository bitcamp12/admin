package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.ApiResponse;

@RestController
@RequestMapping("api/members")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Member>> signUp(@RequestBody MemberDTO memberDTO) {
        System.out.println("Received data: " + memberDTO);
        
        memberService.signUp(memberDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "success", null));
    }

	@PostMapping("login")
	public String login(Model model) {
        return "login";  
    }
	
	@GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MemberDTO>>> searchMembers(@RequestParam("keyword") String keyword) {
		try {
        	List<MemberDTO> members = memberService.searchMembers(keyword);
        	System.out.println("검색 결과: " + members);
        	return ResponseEntity.ok(new ApiResponse<>(200, "검색 성공", members));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse<>(500, "서버 오류 발생: " + e.getMessage(), null));
		}
	}
}
