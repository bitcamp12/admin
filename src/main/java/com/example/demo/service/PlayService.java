package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
}
