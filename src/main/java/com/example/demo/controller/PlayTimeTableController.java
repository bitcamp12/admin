package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PlayTimeTableDTO;
import com.example.demo.repository.PlayTimeTableRepository;
import com.example.demo.service.PlayTimeTableService;
import com.example.demo.util.ApiResponse;


@RestController
@RequestMapping(value="/api/playTimeTables")
public class PlayTimeTableController {
    
	@Autowired
	private PlayTimeTableService playTimeTableService;
	
	@Autowired
	private PlayTimeTableRepository playTimeTableRepository;
	
	
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
    
    @DeleteMapping("{playTimeSeq}")
    public ResponseEntity<ApiResponse<Void>> deleteTimeTable(@PathVariable("playTimeSeq") int playTimeSeq) {
    	try {
    		playTimeTableRepository.deleteById(playTimeSeq);
    		return ResponseEntity.ok().body(new ApiResponse<>(200, "시간표가 삭제됨", null));
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.internalServerError().build();
    	    
    	}
    }
    
    
}
