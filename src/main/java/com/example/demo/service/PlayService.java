package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayDAO;
import com.example.demo.dto.PlayDTO;
import com.example.demo.entity.Play;
import com.example.demo.repository.PlayRepository;

@Service
public class PlayService {

	@Autowired
	private PlayDAO playDAO;
	
	@Autowired
	private PlayRepository playRepository;

	public void playRegisterWrite(PlayDTO playDTO) {
		playDAO.playRegisterWrite(playDTO);
	}

	public List<PlayDTO> getPlayList() {
		return playDAO.getPlayList();
	}

	public Play findById(int playSeq) throws Exception {
		Play play = playRepository.findById(playSeq).orElseThrow(
				()-> new Exception("공연 조회 실패")
				);
		return play;
	}

	public void deleteById(int playSeq) {
		playRepository.deleteById(playSeq);
	}

	// Play 페이징 처리 (검색어 없이)
    public List<Play> getPlayPaging(Pageable pageable) {
        Page<Play> pageResult = playRepository.findAll(pageable); // 모든 Play 데이터를 페이징 처리
        return pageResult.getContent(); // 페이징된 Play 목록 반환
    }

    // 전체 Play 개수 반환
    public long getTotalCount() {
        return playRepository.count();
    }

    // name을 기준으로 전체 Play 개수 반환
    public long getTotalCountKeyword(String value) {
        return playRepository.countByNameContaining(value); // name에 키워드가 포함된 Play 개수 반환
    }

    // name을 기준으로 Play 페이징 처리
    public List<Play> getPlayPagingKeyword(Pageable pageable, String value) {
        Page<Play> pageResult = playRepository.findByNameContaining(value, pageable); // name을 기준으로 검색하여 페이징 처리
        return pageResult.getContent(); // 페이징된 Play 목록 반환
    }


}
