package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReviewBeforeService;
import com.example.demo.util.ApiResponse;


@RestController
@RequestMapping(value="/api/reviewBefores")
public class ReviewBeforeController {
    
	@Autowired
	private ReviewBeforeService reviewBeforeService;
	
	@DeleteMapping("{reviewBeforeSeq}")
	public ResponseEntity<ApiResponse<Void>> deleteReviewBefore(
			@PathVariable("reviewBeforeSeq") int reviewBeforeSeq) {

		try { // 공지 사항 수정
			reviewBeforeService.deleteById(reviewBeforeSeq);
			return ResponseEntity.ok().body(new ApiResponse<>(200, "기대평 삭제에 성공했습니다.", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, "기대평 삭제에 실패했습니다.", null));
		}
	}
}
