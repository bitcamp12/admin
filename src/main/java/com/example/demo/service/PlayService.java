package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayDAO;
import com.example.demo.dto.PlayDTO;

@Service
public class PlayService {

	@Autowired
	private PlayDAO playDAO;

	public void playRegisterWrite(PlayDTO playDTO) {
		playDAO.playRegisterWrite(playDTO);
	}

	public List<PlayDTO> getPlayList() {
		return playDAO.getPlayList();
	}
}
