package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	
	
	// 회원 목록 조회 (페이징)
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getMemberList(
			@RequestParam(name = "page", defaultValue = "1") int page) { 
		try { 
			Map<String, Object> result = memberService.getMembersWithPaging(page); 
			return ResponseEntity.ok(new ApiResponse<>(200, "회원 목록 조회 성공", result)); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(500, "회원 목록 조회 실패: " + e.getMessage(), null)); 
		} 
	}
	
    
    // 회원 검색 (페이징)
    @GetMapping("/searchWithPaging")
    public ResponseEntity<ApiResponse<Map<String, Object>>> searchMembers(
            @RequestParam(name="keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page) {
        try {
            Map<String, Object> result = memberService.searchMembersWithPaging(keyword, page);
            return ResponseEntity.ok(new ApiResponse<>(200, "회원 검색 성공", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "회원 검색 실패: " + e.getMessage(), null));
        }
    }
    
    @PostMapping("/setSeller")
    @ResponseBody
    public void setSeller(@RequestParam(value="memberSeq") String memberSeq) {
        int memberSeqInt = Integer.parseInt(memberSeq);

    	memberService.setSeller(memberSeqInt);
    }
    
    @PostMapping("/cancelSeller")
    @ResponseBody
    public void cancelSeller(@RequestParam(value="memberSeq") String memberSeq) {
        int memberSeqInt = Integer.parseInt(memberSeq);

    	memberService.cancelSeller(memberSeqInt);
    }
    
}