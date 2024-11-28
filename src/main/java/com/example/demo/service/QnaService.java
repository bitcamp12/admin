package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QnaDAO;
import com.example.demo.dto.QnaDTO;
import com.example.demo.entity.Qna;
import com.example.demo.repository.QnaRepository;

@Service
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	@Autowired
	private QnaRepository qnaRepository;
	
	public List<QnaDTO> getQnaList() {
		List<Qna> qnaList = qnaRepository.findAllWithReply();
		List<QnaDTO> qnaDTOList = new ArrayList<>();
		
		for(Qna qna : qnaList) {
			qnaDTOList.add(new QnaDTO(qna));
		}
		
		return qnaDTOList;
	}

	public QnaDTO getQnaDetail(int qnaSeq) {
		return qnaDAO.getQnaDetail(qnaSeq);
	}
}
