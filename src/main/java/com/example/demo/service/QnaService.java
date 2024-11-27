package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QnaDAO;
import com.example.demo.entity.Qna;
import com.example.demo.repository.QnaRepository;

@Service
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	@Autowired
	private QnaRepository qnaRepository;
	
	public List<Qna> getQnaList() {
		return qnaRepository.findAll();
	}
}
