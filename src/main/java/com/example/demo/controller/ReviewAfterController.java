package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ReviewAfterRepository;
import com.example.demo.service.ReviewAfterService;
import com.example.demo.util.ApiResponse;


@RestController
@RequestMapping(value="/api/reviewAfters")
public class ReviewAfterController {
    
	@Autowired
	private ReviewAfterService reviewAfterService;
	
	@Autowired
	private ReviewAfterRepository reviewAfterRepository;
	
	@DeleteMapping("{reviewAfterSeq}")
	public ResponseEntity<ApiResponse<Void>> deleteReviewAfter(
			@PathVariable("reviewAfterSeq") int reviewAfterSeq) {

		try { // 공지 사항 수정
			reviewAfterRepository.deleteById(reviewAfterSeq);
			return ResponseEntity.ok().body(new ApiResponse<>(200, "관람평 삭제에 성공했습니다.", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, "관람평 삭제에 실패했습니다.", null));
		}
	}
}
