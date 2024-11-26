package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReviewBeforeDAO;
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
}
