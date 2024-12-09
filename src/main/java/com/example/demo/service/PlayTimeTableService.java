package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayTimeTableDAO;
import com.example.demo.dto.PlayTimeTableDTO;
import com.example.demo.entity.PlayTimeTable;
import com.example.demo.repository.PlayTimeTableRepository;

@Service
public class PlayTimeTableService {

	@Autowired
	private PlayTimeTableDAO playTimeTableDAO;
	
	@Autowired
	private PlayTimeTableRepository playTimeTableRepository;

	public void timeRegisterWrite(PlayTimeTableDTO playTimeTableDTO) {
		playTimeTableDAO.timeRegisterWrite(playTimeTableDTO);
	}

	public List<Map<String, Object>> timeRegisterFormSelect() {
		return playTimeTableDAO.timeRegisterFormSelect();
	}

	 // 전체 PlayTimeTable 개수 반환
    public long getTotalCount() {
        return playTimeTableRepository.count();
    }

    // 특정 PK 기준 PlayTimeTable 개수 반환
    public long getTotalCountKeyword(String value) {
        try {
            Long id = Long.parseLong(value);
            return playTimeTableRepository.countByPlayTimeTableSeq(id);
        } catch (NumberFormatException e) {
            return 0; // value가 숫자가 아니면 0 반환
        }
    }

    // 페이징 처리된 PlayTimeTable 목록 반환
    public List<PlayTimeTable> getTimePaging(Pageable pageable) {
        Page<PlayTimeTable> pageResult = playTimeTableRepository.findAll(pageable);
        return pageResult.getContent();
    }

    // 특정 PK 기준으로 검색된 PlayTimeTable 목록 반환
    public List<PlayTimeTable> getTimePagingKeyword(Pageable pageable, String value) {
        try {
            Long id = Long.parseLong(value);
            Page<PlayTimeTable> pageResult = playTimeTableRepository.findByPlayTimeTableSeq(id, pageable);
            return pageResult.getContent();
        } catch (NumberFormatException e) {
            return Collections.emptyList(); // value가 숫자가 아니면 빈 리스트 반환
        }
    }
}
