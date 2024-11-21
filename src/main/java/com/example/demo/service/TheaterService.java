package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TheaterDAO;

@Service
public class TheaterService {

	@Autowired
	private TheaterDAO theaterDAO;

	public List<Map<String, Object>> theaterListSelect() {
		return theaterDAO.theaterListSelect();
	}
}
