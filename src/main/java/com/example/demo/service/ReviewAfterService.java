package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReviewAfterRepository;

@Service
public class ReviewAfterService {

	@Autowired
	private ReviewAfterRepository reviewAfterRepository;

	public void deleteById(int reviewAfterSeq) {
		reviewAfterRepository.deleteById(reviewAfterSeq);
	}
}
