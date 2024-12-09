package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReviewBeforeDAO;
import com.example.demo.entity.ReviewBefore;
import com.example.demo.repository.ReviewBeforeRepository;

@Service
public class ReviewBeforeService {

	@Autowired
	private ReviewBeforeDAO reviewBeforeDAO;
	
	@Autowired
	private ReviewBeforeRepository reviewBeforeRepository;
	
	public void deleteById(int reviewBeforeSeq) {
		// TODO Auto-generated method stub
		reviewBeforeRepository.deleteById(reviewBeforeSeq);
	}

	  // 리뷰 페이징 처리 (검색어 없이)
    public List<ReviewBefore> getReviewBeforePaging(Pageable pageable) {
        Page<ReviewBefore> pageResult = reviewBeforeRepository.findAll(pageable); // 모든 리뷰 페이징
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }

    // 전체 리뷰 개수 반환
    public long getTotalCount() {
        return reviewBeforeRepository.count();
    }

    // 검색 키워드를 기준으로 전체 리뷰 개수 반환
    public long getTotalCountKeyword(String value) {
        return reviewBeforeRepository.countByContentContaining(value); // 리뷰 내용에 키워드가 포함된 리뷰 개수 반환
    }

    // 검색 키워드를 기준으로 리뷰 페이징 처리
    public List<ReviewBefore> getReviewBeforePagingKeyword(Pageable pageable, String value) {
        Page<ReviewBefore> pageResult = reviewBeforeRepository.findByContentContaining(value, pageable); // 키워드로 리뷰 검색하여 페이징 처리
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }
}
