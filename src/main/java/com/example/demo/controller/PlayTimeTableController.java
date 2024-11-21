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


@RestController
@RequestMapping(value="/api/playTimeTables")
public class PlayTimeTableController {
    
	@Autowired
	private PlayTimeTableService playTimeTableService;
	
	
	// 공연 시간 정보 입력 
    @PostMapping("/timeRegisterWrite")
    public void timeRegisterWrite(@RequestBody PlayTimeTableDTO playTimeTableDTO) {
    	playTimeTableService.timeRegisterWrite(playTimeTableDTO);
    }
    
    // 공연 정보 조회
    @PostMapping("/timeRegisterFormSelect")
    public List<Map<String, Object>> timeRegisterFormSelect() {
    	return playTimeTableService.timeRegisterFormSelect();
    }
    
    
}
