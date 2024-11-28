package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReplyDTO;
import com.example.demo.service.ReplyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/api/replys")
public class ReplyController {
    
	@Autowired
	private ReplyService replyService;
	
    // QnA 게시판 - 답변 작성
    @PostMapping("/replyWrite")
    public void replyWrite(@RequestBody ReplyDTO replyDTO, HttpSession httpSession) {
    	Integer memberSeq = (Integer) httpSession.getAttribute("memberSeq");
    	
    	replyDTO.setMemberSeq(memberSeq);
    	
    	if(replyDTO != null) {
    		replyService.replyWrite(replyDTO);
    	}
    }
    
    // QnA 게시판 - 답변 수정
    @PostMapping("/replyUpdate")
    public void replyUpdate(@RequestBody ReplyDTO replyDTO, HttpSession httpSession) {
    	Integer memberSeq = (Integer) httpSession.getAttribute("memberSeq");
    	
    	replyDTO.setMemberSeq(memberSeq);
    	
    	if(replyDTO != null) {
    		replyService.replyUpdate(replyDTO);
    	}
    }
}
