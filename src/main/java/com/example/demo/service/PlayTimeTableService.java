package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayTimeTableDAO;
import com.example.demo.dto.PlayTimeTableDTO;

@Service
public class PlayTimeTableService {

	@Autowired
	private PlayTimeTableDAO playTimeTableDAO;

	public void timeRegisterWrite(PlayTimeTableDTO playTimeTableDTO) {
		playTimeTableDAO.timeRegisterWrite(playTimeTableDTO);
	}

	public List<Map<String, Object>> timeRegisterFormSelect() {
		return playTimeTableDAO.timeRegisterFormSelect();
	}
}
