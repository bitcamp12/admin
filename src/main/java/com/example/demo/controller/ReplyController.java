package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReplyDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.ReplyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/api/replys")
public class ReplyController {
    
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReplyService replyService;
	
    // QnA 게시판 - 답변 작성
    @PostMapping("/replyWrite")
    public void replyWrite(@RequestBody ReplyDTO replyDTO, HttpSession httpSession) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Member member  = null;
    	if (authentication != null && authentication.isAuthenticated()) {
    		String username = authentication.getName();
    		member = memberRepository.findById(username);
        	
    	}
    	

    	
    	if(replyDTO != null) {
    		replyDTO.setMemberSeq(member.getMemberSeq());
    		replyService.replyWrite(replyDTO);
    	}
    }
    
    // QnA 게시판 - 답변 수정
    @PostMapping("/replyUpdate")
    public void replyUpdate(@RequestBody ReplyDTO replyDTO, HttpSession httpSession) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Member member = null;
    	if (authentication != null && authentication.isAuthenticated()) {
    		String username = authentication.getName();
    		member = memberRepository.findById(username);

    	}
    	
        	
    	if(replyDTO != null) {
        	replyDTO.setMemberSeq(member.getMemberSeq());
    		replyService.replyUpdate(replyDTO);
    	}
    }
}
