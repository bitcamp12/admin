package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QnaDTO;
import com.example.demo.service.QnaService;


@RestController
@RequestMapping(value="/api/qnas")
public class QnaController {
    
	@Autowired
	private QnaService qnaService;
	
    // QnA 게시판 - 답변 뷰
    @GetMapping("/qnaDetail/{qnaSeq}")
    @ResponseBody
    public ResponseEntity<QnaDTO> qnaDetail(@PathVariable("qnaSeq") int qnaSeq) {
    	QnaDTO qnaDTO = qnaService.getQnaDetail(qnaSeq);
    	
    	if(qnaDTO != null) {
    		return ResponseEntity.ok(qnaDTO); 
    	} else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    }
}
