package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public List<QnaDTO> getQnaList(Pageable pageable) {
		Page<Qna> qnaList = qnaRepository.findAllWithReply(pageable);
		List<QnaDTO> qnaDTOList = new ArrayList<>();
		
		for(Qna qna : qnaList) {
			qnaDTOList.add(new QnaDTO(qna));
		}
		
		return qnaDTOList;
	}

	public QnaDTO getQnaDetail(int qnaSeq) {
		return qnaDAO.getQnaDetail(qnaSeq);
	}

	  // 전체 Qna 개수 반환
    public long getTotalCount() {
        return qnaRepository.count();
    }

    // 제목에 키워드가 포함된 Qna의 개수 반환
    public long getTotalCountKeyword(String value) {
        return qnaRepository.countByTitleContaining(value); // title에서 키워드 포함된 Qna 개수 반환
    }

    // 제목에 키워드가 포함된 Qna 목록 (검색어 기준)
    public List<QnaDTO> getQnaPagingKeyword(Pageable pageable, String value) {
    	Page<Qna> qnaList = qnaRepository.findAllWithReplyKeyword(pageable, value);
		List<QnaDTO> qnaDTOList = new ArrayList<>();
		
		for(Qna qna : qnaList) {
			qnaDTOList.add(new QnaDTO(qna));
		}
		
		return qnaDTOList;
    }
}
