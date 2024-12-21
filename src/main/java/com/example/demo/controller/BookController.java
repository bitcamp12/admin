package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping(value="/api/books")
public class BookController {
    
	@Autowired
	private BookService bookService;
	
	
	
    // QnA 게시판 - 답변 수정
    @PostMapping("/bookDelete")
    @ResponseBody
    public void bookDelete(@RequestParam(value="bookSeq") String bookSeq) {
     int bookSeqInt = Integer.parseInt(bookSeq);

	bookService.bookDelete(bookSeqInt);
    }
}
