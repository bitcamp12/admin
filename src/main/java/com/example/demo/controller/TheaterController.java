package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PlayTimeTableDTO;
import com.example.demo.service.PlayTimeTableService;
import com.example.demo.service.ReviewBeforeService;
import com.example.demo.service.TheaterService;


@RestController
@RequestMapping(value="/api/theaters")
public class TheaterController {
    
	@Autowired
	private TheaterService theaterService;

	// 공연 시간 정보 입력 
    @PostMapping("/theaterListSelect")
    public List<Map<String, Object>> theaterListSelect() {
    	return theaterService.theaterListSelect();
    }
}
